package com.lot.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ProductNotFoundException extends RuntimeException  {

	public ProductNotFoundException(String message) {
		super(message);
	}
}
