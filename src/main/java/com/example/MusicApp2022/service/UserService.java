package com.example.MusicApp2022.service;

import com.example.MusicApp2022.shared.dto.UserDto;

public interface UserService {

	void delete(String id);

	UserDto createUser(UserDto userDto);

}
