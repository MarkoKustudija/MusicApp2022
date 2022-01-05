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

import com.example.MusicApp2022.service.FestivalService;
import com.example.MusicApp2022.shared.dto.FestivalDto;
import com.example.MusicApp2022.ui.request.model.FestivalRequestDetailsModel;
import com.example.MusicApp2022.ui.response.model.FestivalRest;
import com.example.MusicApp2022.ui.response.model.OperationStatusModel;
import com.example.MusicApp2022.ui.response.model.RequestOperationName;
import com.example.MusicApp2022.ui.response.model.RequestOperationStatus;

@RestController
@RequestMapping("/api/v1/festivals")
public class FestivalController {
	
	@Autowired
	FestivalService festivalService;
	
	
	@PostMapping
	public FestivalRest create (@RequestBody FestivalRequestDetailsModel festivalDetails) {
		
		FestivalRest returnValue = new FestivalRest();
		
		ModelMapper modelMapper = new ModelMapper();
			
		FestivalDto festivalDto = modelMapper.map(festivalDetails, FestivalDto.class);
		
		FestivalDto createdFestival = festivalService.create(festivalDto);
		
		
		returnValue = modelMapper.map(createdFestival, FestivalRest.class);
		
		return returnValue;
		
		
	}
	
	@GetMapping(path = "/{id}")
	public FestivalRest getFestival(@PathVariable String id) {
		
		FestivalRest returnValue = new FestivalRest();
		FestivalDto festivalDto = festivalService.getFestivalById(id);
		
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(festivalDto, FestivalRest.class);
		
		return returnValue;
		
	}
	
	@GetMapping
	public List<FestivalRest> getFestivals( @RequestParam (value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit){
		
		List<FestivalRest> returnValue = new ArrayList<>();
		List<FestivalDto> festivals = festivalService.getFestivals(page, limit);
		
		for(FestivalDto festivalDto :  festivals) {
			FestivalRest festivalRest = new FestivalRest();
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.map(festivalDto, festivalRest);
			returnValue.add(festivalRest);
		}
		
		
		return returnValue;
		
		
	}
	
	
	@PutMapping(path = "/{id}")
	public FestivalRest updateFestival(@PathVariable String id, @RequestBody FestivalRequestDetailsModel festivalDetails) {
		
		FestivalRest returnValue = new FestivalRest();
		FestivalDto festivalDto = new FestivalDto();
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(festivalDetails, festivalDto);
		
		FestivalDto updatedFestival = festivalService.update(festivalDto, id);
		returnValue = modelMapper.map(updatedFestival, FestivalRest.class);
		
		return returnValue;
		
	}
	
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel delete(@PathVariable String id) {
		
		OperationStatusModel returnValue = new OperationStatusModel();
		
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		
		festivalService.delete(id);
		
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		return returnValue;
	}

}
