package com.example.MusicApp2022.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MusicApp2022.service.UserService;
import com.example.MusicApp2022.shared.dto.UserDto;
import com.example.MusicApp2022.ui.request.model.UserRequestDetailsModel;
import com.example.MusicApp2022.ui.response.model.OperationStatusModel;
import com.example.MusicApp2022.ui.response.model.RequestOperationName;
import com.example.MusicApp2022.ui.response.model.RequestOperationStatus;
import com.example.MusicApp2022.ui.response.model.UserRest;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping
	public UserRest create (@RequestBody UserRequestDetailsModel userDetails) {
		
		UserRest returnValue = new UserRest();
		UserDto userDto = new UserDto();
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(userDetails, userDto);
		
		UserDto createdUser = userService.createUser(userDto);
		returnValue = modelMapper.map(createdUser, UserRest.class);
		
		return returnValue;
		
	}
	
	@GetMapping(path = "/{id}")
	public UserRest getUser(@PathVariable String id) {
		
		UserRest returnValue = new UserRest();
		UserDto userDto = userService.getUser(id);
		
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(userDto, UserRest.class);
		
		return returnValue;
		
	}
	
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
	
	@PutMapping(path = "/{id}")
	public UserRest update(@PathVariable String id, @RequestBody UserRequestDetailsModel userDetails) {
		
		UserRest returnValue = new UserRest();
		ModelMapper modelMapper = new ModelMapper();
		
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		
		UserDto updatedUser = userService.updateUser(id, userDto);
		returnValue = modelMapper.map(updatedUser, UserRest.class);
		
		return returnValue;
		
	}
	
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel delete(@PathVariable String id) {
		
		OperationStatusModel returnValue = new OperationStatusModel();
		
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		
		userService.delete(id);
		
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		return returnValue;
		
	}
	
	
	
	
	

}
