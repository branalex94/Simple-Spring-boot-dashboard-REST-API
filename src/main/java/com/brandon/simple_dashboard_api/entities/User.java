package com.brandon.simple_dashboard_api.entities;

import java.time.LocalDateTime;

import com.brandon.simple_dashboard_api.utils.UserFieldsValidationConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity()
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Size(min = UserFieldsValidationConstants.NAME_MIN, max = UserFieldsValidationConstants.NAME_MAX)

	@Size(min = UserFieldsValidationConstants.NAME_MIN, max = UserFieldsValidationConstants.NAME_MAX)
	private String firstName;

	@Size(min = UserFieldsValidationConstants.NAME_MIN, max = UserFieldsValidationConstants.NAME_MAX)
	private String secondName;

	@Size(min = UserFieldsValidationConstants.NAME_MIN, max = UserFieldsValidationConstants.NAME_MAX)
	private String lastName;

	@Size(min = UserFieldsValidationConstants.NAME_MIN, max = UserFieldsValidationConstants.NAME_MAX)
	private String secondLastName;

	@Column(unique = true)
	@Size(min = UserFieldsValidationConstants.EMAIL_MIN, max = UserFieldsValidationConstants.EMAIL_MAX)
	private String email;

	@Size(min = UserFieldsValidationConstants.PHONE_MIN, max = UserFieldsValidationConstants.PHONE_MAX)
	private String phone;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public String getFullName() {
		return formatFullName().replaceAll("  ", " ").trim();
	}

	public String formatFullName() {
		return getFirstName() + " " + getSecondName() + " " + getLastName()
				+ " " + getSecondLastName();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		if (secondName == null) {
			return "";
		}
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSecondLastName() {
		if (secondLastName == null) {
			return "";
		}
		return secondLastName;
	}

	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
