package com.brandon.simple_dashboard_api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.brandon.simple_dashboard_api.entities.User;
import com.brandon.simple_dashboard_api.entities.UserDTO;
import com.brandon.simple_dashboard_api.entities.UserResponseDTO;
import com.brandon.simple_dashboard_api.utils.GeneralValidations;
import com.brandon.simple_dashboard_api.utils.Messages;
import com.brandon.simple_dashboard_api.utils.UserFieldsValidationConstants;

@Service()
public class UserService {

	public void validateCreateUserFields(UserDTO user, List<User> users)
			throws ResponseStatusException {
		GeneralValidations.validateString(user.firstName(), "primer nombre",
				UserFieldsValidationConstants.NAME_MIN,
				UserFieldsValidationConstants.NAME_MAX);
		GeneralValidations.validateString(user.lastName(), "primer apellido",
				UserFieldsValidationConstants.NAME_MIN,
				UserFieldsValidationConstants.NAME_MAX);
		GeneralValidations.validateString(user.email(), "correo",
				UserFieldsValidationConstants.EMAIL_MIN,
				UserFieldsValidationConstants.EMAIL_MAX);
		GeneralValidations.validateString(user.phone(), "celular",
				UserFieldsValidationConstants.PHONE_MIN,
				UserFieldsValidationConstants.PHONE_MAX);
		validateUserEmail(user.email(), users);
		if (users.stream().anyMatch(u -> u.getEmail().equals(user.email()))) {
			Messages.throwResponseStatusWithMessage(HttpStatus.BAD_REQUEST,
					"Este correo ya se encuentra registrado.");
		}
	}

	public void validateUpdateUserFields(UserDTO user, User foundUser,
			List<User> users) throws ResponseStatusException {

		GeneralValidations.validateString(user.firstName(), "primer nombre",
				UserFieldsValidationConstants.NAME_MIN,
				UserFieldsValidationConstants.NAME_MAX);
		GeneralValidations.validateString(user.lastName(), "primer apellido",
				UserFieldsValidationConstants.NAME_MIN,
				UserFieldsValidationConstants.NAME_MAX);
		GeneralValidations.validateString(user.email(), "correo",
				UserFieldsValidationConstants.EMAIL_MIN,
				UserFieldsValidationConstants.EMAIL_MAX);
		GeneralValidations.validateString(user.phone(), "celular",
				UserFieldsValidationConstants.PHONE_MIN,
				UserFieldsValidationConstants.PHONE_MAX);

		validateUserEmail(user.email(), users);

		if (users.stream().anyMatch(u -> u.getEmail().equals(user.email()))
				&& !user.email().equals(foundUser.getEmail())) {
			Messages.throwResponseStatusWithMessage(HttpStatus.BAD_REQUEST,
					"Este correo ya se encuentra registrado.");
		}
	}

	public void validateUserEmail(String email, List<User> users) {
		Pattern pattern = Pattern
				.compile(UserFieldsValidationConstants.EMAIL_REGEX_PATTERN);
		Matcher matcher = pattern.matcher(email);
		boolean matches = matcher.find();

		if (!matches) {
			Messages.throwResponseStatusWithMessage(HttpStatus.BAD_REQUEST,
					"Formato de correo no válido.");
		}
	}

	public User generateUserFromDTO(UserDTO user, List<User> users)
			throws ResponseStatusException {

		User newUser = new User();
		LocalDateTime createdDateTime = LocalDateTime.now();
		newUser.setFirstName(user.firstName());
		newUser.setSecondName(user.secondName());
		newUser.setLastName(user.lastName());
		newUser.setSecondLastName(user.secondLastName());
		newUser.setEmail(user.email());
		newUser.setPhone(user.phone());

		newUser.setCreatedAt(createdDateTime);
		newUser.setUpdatedAt(createdDateTime);

		return newUser;
	}

	public UserResponseDTO generateUserResponseDTOfromUser(User user) {
		return new UserResponseDTO(user.getFullName(), user.getFirstName(),
				user.getSecondName(), user.getLastName(),
				user.getSecondLastName(), user.getPhone(), user.getEmail(),
				user.getId());
	}

}
