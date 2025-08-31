package com.brandon.simple_dashboard_api.utils;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class Messages {
	public static void throwResponseStatusWithMessage(HttpStatusCode code,
			String message) {
		throw new ResponseStatusException(code, message);
	}
}
