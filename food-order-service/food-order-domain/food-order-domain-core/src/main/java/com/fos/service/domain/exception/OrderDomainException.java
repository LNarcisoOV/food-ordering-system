package com.fos.service.domain.exception;

import com.fos.exception.DomainException;

public class OrderDomainException extends DomainException {

	public OrderDomainException(String message) {
		super(message);
	}

	public OrderDomainException(String message, Throwable cause) {
		super(message, cause);
	}

}
