package com.example.MusicApp2022.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.MusicApp2022.shared.dto.UserDto;

public interface UserService  extends UserDetailsService{

	void delete(String id);

	UserDto createUser(UserDto userDto);

	UserDto getUser(String email);
		
	List<UserDto> getUsers(int page, int limit);

	UserDto updateUser(String id, UserDto userDto);

	UserDto getUserByUserId(String id);

	boolean requestPasswordReset(String email);

	boolean resetPassword(String token, String password);

	

	

}
