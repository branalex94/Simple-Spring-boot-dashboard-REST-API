package com.brandon.simple_dashboard_api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.brandon.simple_dashboard_api.entities.User;
import com.brandon.simple_dashboard_api.entities.UserDTO;
import com.brandon.simple_dashboard_api.entities.UserResponseDTO;
import com.brandon.simple_dashboard_api.errors.EmailExistsException;
import com.brandon.simple_dashboard_api.repositories.UserRepository;
import com.brandon.simple_dashboard_api.utils.Messages;
import com.brandon.simple_dashboard_api.utils.UserFieldsValidationConstants;

import jakarta.validation.Validator;

@Service()
public class UserService {

	private UserRepository userRepository;
	private Validator validator;

	public UserService(UserRepository userRepository, Validator validator) {
		this.userRepository = userRepository;
	}

	public void validateCreateUserFields(UserDTO user, List<User> users)
			throws ResponseStatusException {
//		GeneralValidations.validateString(user.firstName(), "primer nombre",
//				UserFieldsValidationConstants.NAME_MIN,
//				UserFieldsValidationConstants.NAME_MAX);
//		GeneralValidations.validateString(user.lastName(), "primer apellido",
//				UserFieldsValidationConstants.NAME_MIN,
//				UserFieldsValidationConstants.NAME_MAX);
//		GeneralValidations.validateString(user.email(), "correo",
//				UserFieldsValidationConstants.EMAIL_MIN,
//				UserFieldsValidationConstants.EMAIL_MAX);
//		GeneralValidations.validateString(user.phone(), "celular",
//				UserFieldsValidationConstants.PHONE_MIN,
//				UserFieldsValidationConstants.PHONE_MAX);
//		Set<ConstraintViolation<User>> violations = validator
//				.validate(newUserFromDTO);
//		if (!violations.isEmpty()) {
//			throw new ConstraintViolationException(violations);
//		}
		validateUserEmail(user.email());
		Optional<User> findUser = userRepository.findByEmail(user.email());
		if (!findUser.isEmpty()) {
			Messages.throwResponseStatusWithMessage(HttpStatus.BAD_REQUEST,
					"Este correo ya se encuentra registrado.");
		}
	}

	public void validateUpdateUserFields(UserDTO user, User foundUser,
			List<User> users) throws ResponseStatusException {

//		GeneralValidations.validateString(user.firstName(), "primer nombre",
//				UserFieldsValidationConstants.NAME_MIN,
//				UserFieldsValidationConstants.NAME_MAX);
//		GeneralValidations.validateString(user.lastName(), "primer apellido",
//				UserFieldsValidationConstants.NAME_MIN,
//				UserFieldsValidationConstants.NAME_MAX);
//		GeneralValidations.validateString(user.email(), "correo",
//				UserFieldsValidationConstants.EMAIL_MIN,
//				UserFieldsValidationConstants.EMAIL_MAX);
//		GeneralValidations.validateString(user.phone(), "celular",
//				UserFieldsValidationConstants.PHONE_MIN,
//				UserFieldsValidationConstants.PHONE_MAX);

		validateUserEmail(user.email());

		if (users.stream().anyMatch(u -> u.getEmail().equals(user.email()))
				&& !user.email().equals(foundUser.getEmail())) {
			Messages.throwResponseStatusWithMessage(HttpStatus.BAD_REQUEST,
					"Este correo ya se encuentra registrado.");
		}
	}

	public void validateUserEmail(String email) {
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

	public List<UserResponseDTO> getUsers() {
		return userRepository.findAll().stream()
				.map(u -> generateUserResponseDTOfromUser(u)).toList();
	}

	public UserResponseDTO getUserById(int id) throws ResponseStatusException {
		Optional<User> findUser = userRepository.findById(id);
		if (findUser.isEmpty()) {
			Messages.throwResponseStatusWithMessage(HttpStatus.NOT_FOUND,
					"Usuario no encontrado");
		}
		return generateUserResponseDTOfromUser(findUser.get());
	}

	public void addUser(UserDTO newUser) throws ResponseStatusException {
		User newUserFromDTO = generateUserFromDTO(newUser,
				userRepository.findAll());
		if (!userRepository.findByEmail(newUser.email()).isEmpty()) {
//			Messages.throwResponseStatusWithMessage(HttpStatus.NOT_FOUND,
//					"Este correo ya se encuentra registrado.");
			throw new EmailExistsException(
					"Este correo ya se encuentra registrado.");
		}
		userRepository.save(newUserFromDTO);
	}

	public void updateUser(int id, UserDTO user)
			throws ResponseStatusException {
		Optional<User> findUser = userRepository.findById(id);
		if (findUser.isEmpty()) {
			Messages.throwResponseStatusWithMessage(HttpStatus.NOT_FOUND,
					"Usuario no encontrado");
		}
		Optional<User> userByEmail = userRepository.findByEmail(user.email());
		if (!userByEmail.isEmpty()) {
			Messages.throwResponseStatusWithMessage(HttpStatus.NOT_FOUND,
					"Este correo ya se encuentra registrado");
		}
		User newUser = generateUserFromDTO(user, userRepository.findAll());
		userRepository.save(newUser);
	}

	public void deleteUser(int id) {
		Optional<User> findUser = userRepository.findById(id);
		if (findUser.isEmpty()) {
			Messages.throwResponseStatusWithMessage(HttpStatus.NOT_FOUND,
					"Usuario no encontrado");
		}
		userRepository.deleteById(id);
	}

}
