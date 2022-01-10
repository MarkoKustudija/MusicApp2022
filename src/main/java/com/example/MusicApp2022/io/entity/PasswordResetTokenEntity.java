package com.example.MusicApp2022.io.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "password_reset_tokens")
public class PasswordResetTokenEntity implements Serializable{

	
	private static final long serialVersionUID = 8155962014139720241L;

	@Id
	@GeneratedValue
	private Long id;

	private String token;

	@OneToOne
	@JoinColumn(name = "users_id")
	private UserEntity userDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserEntity getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserEntity userDetails) {
		this.userDetails = userDetails;
	}

}
