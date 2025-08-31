package com.brandon.simple_dashboard_api.utils;

import org.springframework.http.HttpStatus;

public class GeneralValidations {

	public static void validateString(String string, String fieldName,
			int minLength, int maxLength) {
		if (string == null || string.isEmpty()) {
			Messages.throwResponseStatusWithMessage(HttpStatus.BAD_REQUEST,
					String.format(
							ValidationsResponseMessages.FIELD_IS_MANDATORY_RESPONSE_MESSAGE,
							fieldName));
		}
		if (minLength != 0 && string.length() < minLength) {
			Messages.throwResponseStatusWithMessage(HttpStatus.BAD_REQUEST,
					String.format(
							ValidationsResponseMessages.FIELD_MIN_LENGTH_RESPONSE_MESSAGE,
							fieldName, minLength));
		}

		if (maxLength != 0 && string.length() > maxLength) {
			Messages.throwResponseStatusWithMessage(HttpStatus.BAD_REQUEST,
					String.format(
							ValidationsResponseMessages.FIELD_MAX_LENGTH_RESPONSE_MESSAGE,
							fieldName, maxLength));
		}
	}

}
