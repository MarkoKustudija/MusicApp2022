package com.example.MusicApp2022.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MusicApp2022.service.CityService;
import com.example.MusicApp2022.shared.dto.CityDto;
import com.example.MusicApp2022.ui.response.model.CityRest;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {
	
	@Autowired
	CityService cityService;
	
	
	@GetMapping
	public List<CityRest> getCities() {
		
		List<CityRest> returnValue = new ArrayList<>();
		
		List<CityDto> cities = cityService.getCities();
		
		for(CityDto cityDto : cities) {
			CityRest restModel = new CityRest();
			BeanUtils.copyProperties(cityDto, restModel);
			returnValue.add(restModel);
		}
		
		
		return returnValue;
		
	}

}
