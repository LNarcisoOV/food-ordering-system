package com.fos.service.domain.entity;

import com.fos.common.domain.entity.BaseEntity;
import com.fos.common.domain.valueobject.Money;
import com.fos.common.domain.valueobject.OrderId;
import com.fos.service.domain.valueobject.OrderItemId;

public class OrderItem extends BaseEntity<OrderItemId> {

    private OrderId orderId;
    private final Product product;
    private final int quantity;
    private final Money price;
    private final Money subtotal;

    private OrderItem(Builder builder) {
        super.setId(builder.orderItemId);
        this.product = builder.product;
        this.quantity = builder.quantity;
        this.price = builder.price;
        this.subtotal = builder.subtotal;
    }

    void initilize(OrderId orderId, OrderItemId orderItemId) {
        this.orderId = orderId;
        super.setId(orderItemId);
    }

    boolean isPriceValid() {
        return price.isGreaterThanZero() 
                && price.equals(product.getPrice())
                && price.multiply(quantity).equals(subtotal); 
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getSubtotal() {
        return subtotal;

    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private OrderItemId orderItemId;
        private Product product;
        private int quantity;
        private Money price;
        private Money subtotal;

        private Builder() {}

        public Builder withOrderId(OrderItemId orderItemId) {
            this.orderItemId = orderItemId;
            return this;
        }

        public Builder withProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder withPrice(Money price) {
            this.price = price;
            return this;
        }

        public Builder withSubtotal(Money subtotal) {
            this.subtotal = subtotal;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }

}
