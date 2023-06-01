package com.fos.service.domain.valueobject;

import com.fos.common.domain.valueobject.BaseId;

public class OrderItemId extends BaseId<Long> {

	private OrderItemId(Long value) {
		super(value);
	}

}
