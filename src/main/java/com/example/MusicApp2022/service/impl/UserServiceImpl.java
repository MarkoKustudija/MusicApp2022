package com.example.MusicApp2022.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.MusicApp2022.io.entity.PasswordResetTokenEntity;
import com.example.MusicApp2022.io.entity.RoleEntity;
import com.example.MusicApp2022.io.entity.UserEntity;
import com.example.MusicApp2022.io.repository.PasswordResetTokenRepository;
import com.example.MusicApp2022.io.repository.RoleRepository;
import com.example.MusicApp2022.io.repository.UserRepository;
import com.example.MusicApp2022.security.UserPrincipal;
import com.example.MusicApp2022.service.UserService;
import com.example.MusicApp2022.shared.dto.AddressDto;
import com.example.MusicApp2022.shared.dto.UserDto;
import com.example.MusicApp2022.shared.utils.Utils;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	RoleRepository roleRepository;


	@Override
	public UserDto createUser(UserDto userDto) {
		
		UserDto returnValue = new UserDto();
		ModelMapper modelMapper = new ModelMapper();
		
		for(int i=0; i < userDto.getAddresses().size(); i++) {
			
			AddressDto addressDto = userDto.getAddresses().get(i);
			addressDto.setAddressId(utils.generateAddressId(30));
			addressDto.setUserDetails(userDto);
			userDto.getAddresses().set(i, addressDto);
			
				
		}
		
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		
		String publicUserId = utils.generateUserId(30);
		
		userEntity.setUserId(publicUserId);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		userEntity.setEmailVerificationToken(utils.generateEmailVerificationToken(publicUserId));
		userEntity.setEmailVerificationStatus(false);
		
		// Set roles
		Collection<RoleEntity> roles = new HashSet<>();
		
		for(String role : userDto.getRoles()) {
			RoleEntity roleEntity = roleRepository.findByName(role);
			
			if(roleEntity != null) {
				roles.add(roleEntity);
			}
			
		}
		
		userEntity.setRoles(roles);
		
		UserEntity createdUser = userRepository.save(userEntity);
		returnValue = modelMapper.map(createdUser, UserDto.class);
		
		return returnValue;
	}
	

	@Override
	public UserDto getUserByUserId(String id) {
		
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(id);
		
		if(userEntity == null)
			throw new UsernameNotFoundException(id);
		
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(userEntity, UserDto.class);
//		BeanUtils.copyProperties(userEntity, returnValue);
		
		return returnValue;
		
	}
	

	@Override
	public UserDto getUser(String email) {
		
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null)
			throw new UsernameNotFoundException(email);
		
//		ModelMapper modelMapper = new ModelMapper();
//		returnValue = modelMapper.map(userEntity, UserDto.class);
		BeanUtils.copyProperties(userEntity, returnValue);
		
		return returnValue;
	}
	
	@Override
	public List<UserDto> getUsers(int page, int limit) {
		
		List<UserDto> returnValue = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<UserEntity> userPages = userRepository.findAll(pageableRequest);
		
		List<UserEntity> users = userPages.getContent();
		
		for(UserEntity userEntity : users) {
			UserDto userDto = new UserDto();
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.map(userEntity, userDto);
			returnValue.add(userDto);
		}
		return returnValue;
	}
	

	@Override
	public UserDto updateUser(String id, UserDto userDto) {

		UserDto returnValue = new UserDto();
		
		UserEntity userEntity = userRepository.findByUserId(id);
		
		if(userEntity == null)
			throw new RuntimeException("Record not found!");
		
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		
		ModelMapper modelMapper = new ModelMapper();
		UserEntity updatedUser = userRepository.save(userEntity);
		
		returnValue = modelMapper.map(updatedUser, UserDto.class);
		
		return returnValue;
	}
	
	
	
	@Override
	public void delete(String id) {

		UserEntity userEntity = userRepository.findByUserId(id);
		
		if(userEntity == null)
			throw new RuntimeException("Record not found!");

		
		userRepository.delete(userEntity);
		
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null) 
			throw new UsernameNotFoundException(email);
		
//		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
		return new UserPrincipal(userEntity);
	}


	@Override
	public boolean requestPasswordReset(String email) {

		boolean returnValue = false;
		
		UserEntity userEntity = userRepository.findByEmail(email);
		

		if(userEntity == null) 
		   throw new RuntimeException("Record doesn't exist!");
		
		String token = new Utils().generatePasswordResetToken(userEntity.getUserId());
		
		PasswordResetTokenEntity passwordResetTokenEntity = new PasswordResetTokenEntity();
		passwordResetTokenEntity.setToken(token);
		passwordResetTokenEntity.setUserDetails(userEntity);
		passwordResetTokenRepository.save(passwordResetTokenEntity);
		
		
		
		return returnValue;
	}


	@Override
	public boolean resetPassword(String token, String password) {
		boolean returnValue = false;
		
		if(Utils.hasTokenExpired(token) ) {
			return returnValue;
		}
		
		PasswordResetTokenEntity passwordResetTokenEntity = passwordResetTokenRepository.findByToken(token);
		
		if(passwordResetTokenEntity == null) {
			return returnValue;
		}
		
		// Prepare new password
		String encodedPassword = bCryptPasswordEncoder.encode(password);
		
		//Update user password in database
		UserEntity userEntity = passwordResetTokenEntity.getUserDetails();
		userEntity.setEncryptedPassword(encodedPassword);
		UserEntity savedUserEntity = userRepository.save(userEntity);
		
		// Verify if password was saved successfully
		if(savedUserEntity != null && savedUserEntity.getEncryptedPassword().equalsIgnoreCase(encodedPassword)) {
		 
			returnValue = true;
		}
		
		// Remove Password reset token from database
		passwordResetTokenRepository.delete(passwordResetTokenEntity);
		
		
		return returnValue;
	}




	

	
}
