package com.brandon.simple_dashboard_api.errors;

import java.time.LocalDateTime;
import java.util.List;

public class ApiErrorResponse {

	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
	private List<FieldErrorDetail> errors;

	public ApiErrorResponse(int status, String error, String message,
			String path, List<FieldErrorDetail> errors) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
		this.errors = errors;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<FieldErrorDetail> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldErrorDetail> errors) {
		this.errors = errors;
	}

}
