package com.example.MusicApp2022.ui.response.model;

import java.util.List;

public class UserRest {

	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<AddressRest> addresses;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<AddressRest> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressRest> addresses) {
		this.addresses = addresses;
	}

}
