package com.brandon.simple_dashboard_api.entities;

import com.brandon.simple_dashboard_api.utils.UserFieldsValidationConstants;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDTO(
		@NotNull @Size(min = UserFieldsValidationConstants.NAME_MIN, max = UserFieldsValidationConstants.NAME_MAX) String firstName,
		@Size(min = UserFieldsValidationConstants.NAME_MIN, max = UserFieldsValidationConstants.NAME_MAX) String secondName,

		@NotNull @Size(min = UserFieldsValidationConstants.NAME_MIN, max = UserFieldsValidationConstants.NAME_MAX) String lastName,

		@Size(min = UserFieldsValidationConstants.NAME_MIN, max = UserFieldsValidationConstants.NAME_MAX) String secondLastName,

		@NotNull @Pattern(regexp = UserFieldsValidationConstants.EMAIL_REGEX_PATTERN, message = "Formato de correo inválido") @Size(min = UserFieldsValidationConstants.EMAIL_MIN, max = UserFieldsValidationConstants.EMAIL_MAX) String email,
		@NotNull @Size(min = UserFieldsValidationConstants.PHONE_MIN, max = UserFieldsValidationConstants.PHONE_MAX) String phone) {

}
