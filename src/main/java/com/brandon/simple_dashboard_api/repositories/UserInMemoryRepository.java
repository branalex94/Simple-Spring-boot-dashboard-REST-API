package com.brandon.simple_dashboard_api.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import com.brandon.simple_dashboard_api.entities.User;
import com.brandon.simple_dashboard_api.entities.UserDTO;
import com.brandon.simple_dashboard_api.entities.UserResponseDTO;
import com.brandon.simple_dashboard_api.services.UserService;
import com.brandon.simple_dashboard_api.utils.Messages;

@Repository()
public class UserInMemoryRepository {
	List<User> users = new ArrayList<User>();

	private int idCounter = 1;

	private UserService userService;

	public UserInMemoryRepository(UserService userService) {
		this.userService = userService;
	}

	public List<UserResponseDTO> getUsers() {
		return users.stream()
				.map(u -> userService.generateUserResponseDTOfromUser(u))
				.toList();
	}

	public UserResponseDTO getUserById(int id) throws ResponseStatusException {
		Optional<User> findUser = users.stream().filter(u -> u.getId() == id)
				.findFirst();
		if (findUser.isEmpty()) {
			Messages.throwResponseStatusWithMessage(HttpStatus.NOT_FOUND,
					"Usuario no encontrado");
		}
		return userService.generateUserResponseDTOfromUser(findUser.get());
	}

	public void addUser(UserDTO newUser) throws ResponseStatusException {
		userService.validateCreateUserFields(newUser, users);
		User newUserFromDTO = userService.generateUserFromDTO(newUser, users);
		newUserFromDTO.setId(idCounter);
		idCounter++;
		users.add(newUserFromDTO);
	}

	public void updateUser(int id, UserDTO user)
			throws ResponseStatusException {
		Optional<User> findUser = users.stream().filter(u -> u.getId() == id)
				.findFirst();
		if (findUser.isEmpty()) {
			Messages.throwResponseStatusWithMessage(HttpStatus.NOT_FOUND,
					"Usuario no encontrado");
		}
		userService.validateUpdateUserFields(user, findUser.get(), users);
		User newUser = userService.generateUserFromDTO(user, users);
		newUser.setId(findUser.get().getId());
		int userIndex = users.indexOf(findUser.get());
		users.set(userIndex, newUser);
	}

	public void deleteUser(int id) {
		Optional<User> findUser = users.stream().filter(u -> u.getId() == id)
				.findFirst();
		if (findUser.isEmpty()) {
			Messages.throwResponseStatusWithMessage(HttpStatus.NOT_FOUND,
					"Usuario no encontrado");
		}
		int userIndex = users.indexOf(findUser.get());
		users.remove(userIndex);
	}
}
