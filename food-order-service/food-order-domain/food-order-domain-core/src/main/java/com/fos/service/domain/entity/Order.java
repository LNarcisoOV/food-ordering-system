package com.fos.service.domain.entity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.fos.common.domain.entity.AggregateRoot;
import com.fos.common.domain.valueobject.CustomerId;
import com.fos.common.domain.valueobject.Money;
import com.fos.common.domain.valueobject.OrderId;
import com.fos.common.domain.valueobject.OrderStatus;
import com.fos.common.domain.valueobject.RestaurantId;
import com.fos.service.domain.exception.OrderDomainException;
import com.fos.service.domain.valueobject.OrderItemId;
import com.fos.service.domain.valueobject.StreetAddress;
import com.fos.service.domain.valueobject.TrackingId;

public class Order extends AggregateRoot<OrderId> {
	private final CustomerId customerId;
	private final RestaurantId restaurantId;
	private final StreetAddress deliveryAddress;
	private final Money price;
	private final List<OrderItem> items;

	private TrackingId trackingId;
	private OrderStatus orderStatus;
	private List<String> failureMessages;

	public void initialize() {
		setId(new OrderId(UUID.randomUUID()));
		trackingId = new TrackingId(UUID.randomUUID());
		orderStatus = OrderStatus.PENDING;
		initializeItems();
	}

	public void validate() {
		validateInitialOrder();
		validateTotalPrice();
		validateItemsPrice();
	}

	private void validateInitialOrder() {
		if (orderStatus != null || getId() != null) {
			throw new OrderDomainException("ORDER IS NOT IN THE CORRECT STATE FOR INITALIZATION.");
		}

	}

	private void validateTotalPrice() {
		if (price == null || !price.isGreaterThanZero()) {
			throw new OrderDomainException("TOTAL PRICE MUST BE GREATER THAN ZERO.");
		}

	}

	private void validateItemsPrice() {
		Money orderItemsTotal = items.stream().map(oi -> {
			validateItemPrice(oi);
			return oi.getSubTotal();
		}).reduce(Money.ZERO, Money::add);

		if (!price.equals(orderItemsTotal)) {
			throw new OrderDomainException("TOTAL PRICE: " + price.getAmount() + " IS NOT EQUAL TO ORDER ITEMS TOTAL: "
					+ orderItemsTotal + "!");
		}
	}

	private void validateItemPrice(OrderItem orderItem) {
	    if(!orderItem.isPriceValid()) {
	        throw new OrderDomainException("ORDER ITEM PRICE: " + orderItem.getPrice().getAmount() + " IS NOT VALID FOR PRODUCT: "
                    + orderItem.getProduct().getId().getValue() + "!");
	    }
	}

	private void initializeItems() {
		long itemId = 1;
		for (OrderItem item : items) {
			item.initilize(super.getId(), new OrderItemId(itemId++));
		}

	}

	private Order(Builder builder) {
		super.setId(builder.orderId);
		this.customerId = builder.customerId;
		this.restaurantId = builder.restaurantId;
		this.deliveryAddress = builder.deliveryAddress;
		this.price = builder.price;
		this.items = builder.items;
		this.trackingId = builder.trackingId;
		this.orderStatus = builder.orderStatus;
		this.failureMessages = builder.failureMessages;
	}

	public CustomerId getCustomerId() {
		return customerId;
	}

	public RestaurantId getRestaurantId() {
		return restaurantId;
	}

	public StreetAddress getDeliveryAddress() {
		return deliveryAddress;
	}

	public Money getPrice() {
		return price;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public TrackingId getTrackingId() {
		return trackingId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public List<String> getFailureMessages() {
		return failureMessages;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private OrderId orderId;
		private CustomerId customerId;
		private RestaurantId restaurantId;
		private StreetAddress deliveryAddress;
		private Money price;
		private List<OrderItem> items = Collections.emptyList();
		private TrackingId trackingId;
		private OrderStatus orderStatus;
		private List<String> failureMessages = Collections.emptyList();

		private Builder() {
		}

		public Builder withOrderId(OrderId orderId) {
			this.orderId = orderId;
			return this;
		}

		public Builder withCustomerId(CustomerId customerId) {
			this.customerId = customerId;
			return this;
		}

		public Builder withRestaurantId(RestaurantId restaurantId) {
			this.restaurantId = restaurantId;
			return this;
		}

		public Builder withDeliveryAddress(StreetAddress deliveryAddress) {
			this.deliveryAddress = deliveryAddress;
			return this;
		}

		public Builder withPrice(Money price) {
			this.price = price;
			return this;
		}

		public Builder withItems(List<OrderItem> items) {
			this.items = items;
			return this;
		}

		public Builder withTrackingId(TrackingId trackingId) {
			this.trackingId = trackingId;
			return this;
		}

		public Builder withOrderStatus(OrderStatus orderStatus) {
			this.orderStatus = orderStatus;
			return this;
		}

		public Builder withFailureMessages(List<String> failureMessages) {
			this.failureMessages = failureMessages;
			return this;
		}

		public Order build() {
			return new Order(this);
		}
	}

}
