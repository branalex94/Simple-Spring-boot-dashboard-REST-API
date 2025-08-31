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
import com.brandon.simple_dashboard_api.repositories.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("")
	public List<UserResponseDTO> getUsers() {
		return userRepository.getUsers();
	}

	@GetMapping("/{id}")
	public UserResponseDTO getUserById(@PathVariable int id) {
		return userRepository.getUserById(id);
	}

	@PostMapping("")
	public void addUser(@RequestBody UserDTO user) {
		userRepository.addUser(user);
	}

	@PutMapping("/{id}")
	public void updateUser(@PathVariable int id, @RequestBody UserDTO user) {
		userRepository.updateUser(id, user);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable int id) {
		this.userRepository.deleteUser(id);
	}
}
