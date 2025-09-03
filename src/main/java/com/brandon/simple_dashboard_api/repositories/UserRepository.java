package com.brandon.simple_dashboard_api.repositories;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.brandon.simple_dashboard_api.entities.User;

public interface UserRepository extends ListCrudRepository<User, Integer> {
	Optional<User> findByEmail(String email);
}
