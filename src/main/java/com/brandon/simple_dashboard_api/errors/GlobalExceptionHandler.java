package com.brandon.simple_dashboard_api.errors;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// Errores de jakarta con el @Valid en el controller
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationErrors(
			MethodArgumentNotValidException ex, HttpServletRequest req) {

		List<Map<String, String>> errorsList = ex.getBindingResult()
				.getFieldErrors().stream().map(err -> Map.of("field",
						err.getField(), "message", err.getDefaultMessage()))
				.toList();

		Map<String, Object> body = new LinkedHashMap<>();

		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
		body.put("message", "Validation error");
		body.put("path", req.getRequestURI());
		body.put("errors", errorsList);

		return ResponseEntity.badRequest().body(body);
	}

	// Errores de jpa con errores en las validaciones de entidad
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, Object>> handleConstraintViolations(
			ConstraintViolationException ex, HttpServletRequest req) {

		List<Map<String, String>> errorsList = ex.getConstraintViolations()
				.stream()
				.map(v -> Map.of("field", v.getPropertyPath().toString(),
						"message", v.getMessage()))
				.toList();

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
		body.put("message", "Validation error");
		body.put("path", req.getRequestURI());
		body.put("errors", errorsList);

		return ResponseEntity.badRequest().body(body);
	}

	@ExceptionHandler(EmailExistsException.class)
	public ResponseEntity<ApiErrorResponse> handleEmailExists(
			EmailExistsException ex, HttpServletRequest req) {

		ApiErrorResponse response = new ApiErrorResponse(
				HttpStatus.CONFLICT.value(),
				HttpStatus.CONFLICT.getReasonPhrase(), ex.getMessage(),
				req.getRequestURI(),
				List.of(new FieldErrorDetail("email", ex.getMessage())));

		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

	}

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiErrorResponse> handleCustomApiException(
			ApiException ex, HttpServletRequest request) {

		ApiErrorResponse response = new ApiErrorResponse(ex.getStatus().value(),
				ex.getStatus().getReasonPhrase(), ex.getMessage(),
				request.getRequestURI(),
				List.of(new FieldErrorDetail(ex.getField(), ex.getMessage())));

		return ResponseEntity.status(ex.getStatus()).body(response);
	}

}
