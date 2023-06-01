package com.fos.service.domain.entity;

import java.util.List;

import com.fos.common.domain.entity.AggregateRoot;
import com.fos.common.domain.valueobject.CustomerId;
import com.fos.common.domain.valueobject.Money;
import com.fos.common.domain.valueobject.OrderId;
import com.fos.common.domain.valueobject.OrderStatus;
import com.fos.common.domain.valueobject.RestaurantId;
import com.fos.service.domain.valueobject.StreetAddress;
import com.fos.service.domain.valueobject.TrackingId;
import java.util.Collections;

public class Order extends AggregateRoot<OrderId> {
	private final CustomerId customerId;
	private final RestaurantId restaurantId;
	private final StreetAddress deliveryAddress;
	private final Money price;
	private final List<OrderItem> items;

	private TrackingId trackingId;
	private OrderStatus orderStatus;
	private List<String> failureMessages;

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
