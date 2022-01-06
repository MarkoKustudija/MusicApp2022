package com.example.MusicApp2022.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MusicApp2022.io.entity.AddressEntity;
import com.example.MusicApp2022.io.entity.UserEntity;
import com.example.MusicApp2022.io.repository.AddressRepository;
import com.example.MusicApp2022.io.repository.UserRepository;
import com.example.MusicApp2022.service.AddressService;
import com.example.MusicApp2022.shared.dto.AddressDto;
import com.example.MusicApp2022.shared.utils.Utils;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired 
	UserRepository userRepository;

	@Autowired
	Utils utils;
	
	@Override
	public List<AddressDto> getAddresses(String id) {
		
		List<AddressDto> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		
		UserEntity userEntity = userRepository.findByUserId(id);
		if(userEntity == null) return returnValue;
		
		Iterable<AddressEntity> addresses = addressRepository.findAllByUserDetails(userEntity);
		
		for(AddressEntity addressEntity : addresses) {
			AddressDto addressDto = new AddressDto();
			modelMapper.map(addressEntity, addressDto);
			returnValue.add(addressDto);
			
		}
		return returnValue;
	}

	@Override
	public AddressDto getAddressById(String addressId) {

		AddressDto returnValue = new AddressDto();
		AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
		
		if(addressEntity == null)
			throw new RuntimeException("Record doesn't exist!");
		
		String publicAddressId = utils.generateAddressId(30);
		addressEntity.setAddressId(publicAddressId);
		
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(addressEntity, AddressDto.class);
		
		return returnValue;
	}
	
	

}
