package com.example.MusicApp2022.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MusicApp2022.io.entity.UserEntity;
import com.example.MusicApp2022.io.repository.UserRepository;
import com.example.MusicApp2022.service.UserService;
import com.example.MusicApp2022.shared.dto.AddressDto;
import com.example.MusicApp2022.shared.dto.UserDto;
import com.example.MusicApp2022.shared.utils.Utils;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;



	@Override
	public UserDto createUser(UserDto userDto) {
		
		UserDto returnValue = new UserDto();
		ModelMapper modelMapper = new ModelMapper();
		
		for(int i=0; i < userDto.getAddresses().size(); i++) {
			
			AddressDto addressDto = userDto.getAddresses().get(i);
			addressDto.setAddressesId(utils.generateAddressId(30));
			addressDto.setUserDetails(userDto);
			userDto.getAddresses().set(i, addressDto);
				
		}
		
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		
		String publicUserId = utils.generateUserId(30);
		userEntity.setUserId(publicUserId);
		
		UserEntity createdUser = userRepository.save(userEntity);
		returnValue = modelMapper.map(createdUser, UserDto.class);
		
		return returnValue;
	}
	
	@Override
	public void delete(String id) {

		UserEntity userEntity = userRepository.findByUserId(id);
		
		if(userEntity == null)
			throw new RuntimeException("Record not found!");

		
		userRepository.delete(userEntity);
		
	}
}
