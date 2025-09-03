package com.brandon.simple_dashboard_api.errors;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
	private final HttpStatus status;
	private final String field;

	public ApiException(HttpStatus status, String field, String message) {
		super(message);
		this.status = status;
		this.field = field;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getField() {
		return field;
	}
}
