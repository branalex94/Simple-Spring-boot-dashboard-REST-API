package com.brandon.simple_dashboard_api.entities;

import java.util.List;

public class Doctor extends User {
	private List<Integer> patients;
	private List<String> roles;

	public List<Integer> getPatients() {
		return patients;
	}

	public void setPatients(List<Integer> patients) {
		this.patients = patients;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
