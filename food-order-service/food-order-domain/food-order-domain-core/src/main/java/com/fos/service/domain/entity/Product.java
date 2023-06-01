package com.fos.service.domain.entity;

import java.util.Objects;

import com.fos.common.domain.entity.BaseEntity;
import com.fos.common.domain.valueobject.Money;
import com.fos.common.domain.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {
	private String name;
	private Money price;

	private Product(ProductId productId, String name, Money price) {
		super.setId(productId);
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public Money getPrice() {
		return price;
	}
}
