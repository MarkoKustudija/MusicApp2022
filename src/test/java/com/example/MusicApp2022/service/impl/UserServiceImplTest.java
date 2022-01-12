package com.example.MusicApp2022.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.MusicApp2022.io.entity.AddressEntity;
import com.example.MusicApp2022.io.entity.RoleEntity;
import com.example.MusicApp2022.io.entity.UserEntity;
import com.example.MusicApp2022.io.repository.RoleRepository;
import com.example.MusicApp2022.io.repository.UserRepository;
import com.example.MusicApp2022.shared.dto.AddressDto;
import com.example.MusicApp2022.shared.dto.UserDto;
import com.example.MusicApp2022.shared.utils.Utils;

class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;
	
	@Mock
	RoleRepository roleRepository;
	
	@Mock
	Utils utils;
	
	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;

	UserEntity userEntity;

	String encryptedPassword = "jd8u2h9fh9fh2h";
	String userId = "ja9uf93u9u49";
	String addressId = "mdkad03i030fj";
	String emailVerificationToken = "jd993k3kfkkdk0";

	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		// User Entity - stub object

		userEntity = new UserEntity();
		userEntity.setId(1L);
		userEntity.setFirstName("James");
		userEntity.setLastName("Bond");
		userEntity.setEncryptedPassword(encryptedPassword);
		userEntity.setUserId(userId);
		userEntity.setEmail("test@test.com");
		userEntity.setEmailVerificationToken(emailVerificationToken);
		userEntity.setAddresses(getAddressEntity());
		userEntity.setRoles(getRoleEntity());
	}

	@Test
	void testGetUser() {

		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);

		UserDto userDto = userServiceImpl.getUser("test@test.com");

		assertNotNull(userDto);
		assertEquals("James", userDto.getFirstName());

	}

	@Test
	void testGetUser_UsernameNotFoundException() {
		
		when(userRepository.findByEmail(anyString())).thenReturn(null);

		assertThrows(UsernameNotFoundException.class,

				() -> {
					userServiceImpl.getUser("test@test.com");

				});

	}
	
	@Test
	void testCreateUser() {
	
		when(userRepository.findByEmail(anyString())).thenReturn(null);
	    when(roleRepository.findByName(anyString())).thenReturn(null);
		when(utils.generateUserId(anyInt())).thenReturn(userId);
		when(utils.generateAddressId(anyInt())).thenReturn(addressId);
		when(bCryptPasswordEncoder.encode(anyString())).thenReturn(encryptedPassword);
	
		when(userRepository.save(any())).thenReturn(userEntity);
		
		UserDto userDto = new UserDto();
		userDto.setFirstName("James");
		userDto.setLastName("Bond");
		userDto.setEmail("test@test.com");
		userDto.setPassword("007");
		userDto.setAddresses(getAddressesDto());
		userDto.setRoles(getRoles());
		
		UserDto storedUserDto = userServiceImpl.createUser(userDto);
		
		assertNotNull(storedUserDto);
		assertNotNull(storedUserDto.getUserId());
		assertEquals(userEntity.getFirstName(),storedUserDto.getFirstName());
		assertEquals(userEntity.getLastName(), storedUserDto.getLastName());
		assertEquals(userEntity.getAddresses().size(), storedUserDto.getAddresses().size());
		verify(utils, times(storedUserDto.getAddresses().size())).generateAddressId(30);
		verify(bCryptPasswordEncoder, times(1)).encode("007");
		verify(userRepository, times(1)).save(any(UserEntity.class));
		
	}
	
	@Test
	void testCreateUser_CreateIllegalStateException() {
		
		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);

		UserDto userDto = new UserDto();
		userDto.setFirstName("James");
		userDto.setLastName("Bond");
		userDto.setEmail("test@test.com");
		userDto.setPassword("007");
		userDto.setAddresses(getAddressesDto());
		userDto.setRoles(getRoles());
		
		assertThrows(IllegalStateException.class,

				() -> {

					userServiceImpl.createUser(userDto);

				});
	}
	


	private List<AddressDto> getAddressesDto() {

		AddressDto shippingAddress = new AddressDto();
		shippingAddress.setAddressId(addressId);
		shippingAddress.setCity("Vancuver");
		shippingAddress.setCountry("Canada");
		shippingAddress.setStreetName("AAA");
		shippingAddress.setPostalCode("8991000");
		shippingAddress.setType("shipping");

		AddressDto billingAddress = new AddressDto();
		billingAddress.setAddressId(addressId);
		billingAddress.setCity("Vancuver");
		billingAddress.setCountry("Canada");
		billingAddress.setStreetName("BBB");
		billingAddress.setPostalCode("8991000");
		billingAddress.setType("billing");

		List<AddressDto> addresses = new ArrayList<>();
		addresses.add(shippingAddress);
		addresses.add(billingAddress);

		return addresses;

	}

	private List<AddressEntity> getAddressEntity() {

		List<AddressDto> addresses = getAddressesDto();

		Type listType = new TypeToken<List<AddressEntity>>() {
		}.getType();

		return new ModelMapper().map(addresses, listType);

	}
	
	
	
	private List<RoleEntity> getRoleEntity(){
		
	    Collection<String> roles = getRoles();
	    
	    Type listType = new TypeToken<List<RoleEntity>>() {
		}.getType();
	    
	    
		return new ModelMapper().map(roles, listType);
		
		
	}

	private Collection<String> getRoles() {

		Collection<String> roles = new HashSet<>();
		
		return roles;
	}

}
