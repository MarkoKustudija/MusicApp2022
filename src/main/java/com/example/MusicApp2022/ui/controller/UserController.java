package com.example.MusicApp2022.ui.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MusicApp2022.service.AddressService;
import com.example.MusicApp2022.service.UserService;
import com.example.MusicApp2022.shared.dto.AddressDto;
import com.example.MusicApp2022.shared.dto.UserDto;
import com.example.MusicApp2022.shared.utils.Roles;
import com.example.MusicApp2022.ui.request.model.PasswordResetModel;
import com.example.MusicApp2022.ui.request.model.PasswordResetRequestModel;
import com.example.MusicApp2022.ui.request.model.UserRequestDetailsModel;
import com.example.MusicApp2022.ui.response.model.AddressRest;
import com.example.MusicApp2022.ui.response.model.OperationStatusModel;
import com.example.MusicApp2022.ui.response.model.RequestOperationName;
import com.example.MusicApp2022.ui.response.model.RequestOperationStatus;
import com.example.MusicApp2022.ui.response.model.UserRest;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AddressService addressService;
	
	@PostMapping
	public UserRest create (@RequestBody UserRequestDetailsModel userDetails) {
		
		UserRest returnValue = new UserRest();
		UserDto userDto = new UserDto();
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(userDetails, userDto);
		userDto.setRoles(new HashSet<>(Arrays.asList(Roles.ROLE_USER.name())));
		
		UserDto createdUser = userService.createUser(userDto);
		
		returnValue = modelMapper.map(createdUser, UserRest.class);
		
		return returnValue;
		
	}
	
     ///// RESET PASSWORD //////
/**	  http://localhost:8080/api/v1/users/password-reset-request   */
	
	@PostMapping(path = "/password-reset-request")
	public OperationStatusModel requestReset(@RequestBody PasswordResetRequestModel passwordResetRequestModel) {
		
		OperationStatusModel returnValue = new OperationStatusModel();
		
		boolean operationResult = userService.requestPasswordReset(passwordResetRequestModel.getEmail());
		
		returnValue.setOperationName(RequestOperationName.REQUEST_PASSWORD_RESET.name());
		returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
		
		if(!operationResult) {
			 returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
			}
		

		return returnValue;
		
	}
	

/**	  http://localhost:8080/api/v1/users/password-reset */
	@PostMapping(path = "/password-reset")
	public OperationStatusModel resetPassword(@RequestBody PasswordResetModel passwordResetModel) {
		
		
		OperationStatusModel returnValue = new OperationStatusModel();
		
		boolean operationResult = userService.resetPassword(
				
				passwordResetModel.getToken(),
				passwordResetModel.getPassword());
		
		returnValue.setOperationName(RequestOperationName.RESET_PASSWORD.name());
		returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
		
		if(operationResult) {
			 returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
			}
		

		return returnValue;
		
	}
	
 ///////////////////////////////////////////////////////////////////	
	
	@PostAuthorize("hasRole('ADMIN') or returnObject.userId == principal.userId")
	@GetMapping(path = "/{id}")
	public UserRest getUser(@PathVariable String id) {
		
		UserRest returnValue = new UserRest();
		UserDto userDto = userService.getUserByUserId(id);
		
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(userDto, UserRest.class);
		
		return returnValue;
		
	}
	
	@GetMapping(path = "/{id}/addresses")
	public List<AddressRest> getAddresses(@PathVariable String id){
		
		List<AddressRest> returnValue = new ArrayList<>();
		
		List<AddressDto> addresses = addressService.getAddresses(id);
	
		for(AddressDto addressDto : addresses) {
			AddressRest restModel = new AddressRest();
		    ModelMapper modelMapper = new ModelMapper();
		    modelMapper.map(addressDto, restModel);
		    returnValue.add(restModel);
		}
		
		return returnValue;
		
	}
	
	@GetMapping(path = "/{id}/addresses/{addressId}")
	public AddressRest getAddress(@PathVariable String addressId) {
		
		AddressRest returnValue = new AddressRest();
		AddressDto addressDto = addressService.getAddressById(addressId);
		
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(addressDto, AddressRest.class);
		
		return returnValue;
		
	}
	
	@PostAuthorize("hasRole('ADMIN')")
	@GetMapping
	public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0")int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit){
		
		List<UserRest> returnValue = new ArrayList<>();
		List<UserDto> users = userService.getUsers(page, limit);
		
		for(UserDto userDto : users) {
			UserRest modelRest = new UserRest();
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.map(userDto, modelRest);
			returnValue.add(modelRest);
		}
		
		
		return returnValue;
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or #id == principal.userId")
	@PutMapping(path = "/{id}")
	public UserRest update(@PathVariable String id, @RequestBody UserRequestDetailsModel userDetails) {
		
		UserRest returnValue = new UserRest();
		ModelMapper modelMapper = new ModelMapper();
		
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		
		UserDto updatedUser = userService.updateUser(id, userDto);
		returnValue = modelMapper.map(updatedUser, UserRest.class);
		
		return returnValue;
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or #id == principal.userId")
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel delete(@PathVariable String id) {
		
		OperationStatusModel returnValue = new OperationStatusModel();
		
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		
		userService.delete(id);
		
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		return returnValue;
		
	}
	
	
	
	
	

}
