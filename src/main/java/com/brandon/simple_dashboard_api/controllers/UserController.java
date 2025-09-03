package com.brandon.simple_dashboard_api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brandon.simple_dashboard_api.entities.UserDTO;
import com.brandon.simple_dashboard_api.entities.UserResponseDTO;
import com.brandon.simple_dashboard_api.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("")
	public List<UserResponseDTO> getUsers() {
		return userService.getUsers();
	}

	@GetMapping("/{id}")
	public UserResponseDTO getUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}

	@PostMapping("")
	public void addUser(@Valid @RequestBody UserDTO user) {
		userService.addUser(user);
	}

	@PutMapping("/{id}")
	public void updateUser(@PathVariable int id,
			@Valid @RequestBody UserDTO user) {
		userService.updateUser(id, user);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}
}
