package com.example.MusicApp2022.service;

import java.util.List;

import com.example.MusicApp2022.shared.dto.UserDto;

public interface UserService {

	void delete(String id);

	UserDto createUser(UserDto userDto);

	UserDto getUser(String id);

	List<UserDto> getUsers(int page, int limit);

	UserDto updateUser(String id, UserDto userDto);

}
