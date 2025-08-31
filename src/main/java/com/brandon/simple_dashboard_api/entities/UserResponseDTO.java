package com.brandon.simple_dashboard_api.entities;

import io.micrometer.common.lang.Nullable;

public record UserResponseDTO(String fullName, String firstName,
		@Nullable() String secondName, String lastName,
		@Nullable() String secondLastName, String phone, String email, int id) {

}
