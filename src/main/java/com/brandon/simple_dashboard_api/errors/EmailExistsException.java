package com.brandon.simple_dashboard_api.errors;

import org.springframework.http.HttpStatus;

public class EmailExistsException extends ApiException {
	public EmailExistsException(String message) {
		super(HttpStatus.CONFLICT, "email", message);
	}
}
