package com.example.MusicApp2022.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MusicApp2022.io.entity.CityEntity;
import com.example.MusicApp2022.io.repository.CityRepository;
import com.example.MusicApp2022.service.CityService;
import com.example.MusicApp2022.shared.dto.CityDto;

@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	CityRepository cityRepository;

	@Override
	public List<CityDto> getCities() {
		List<CityDto> returnValue = new ArrayList<>();
		Iterable <CityEntity> cities = cityRepository.findAll();
		
		for(CityEntity cityEntity : cities) {
			CityDto cityDto = new CityDto();
			BeanUtils.copyProperties(cityEntity, cityDto);
			returnValue.add(cityDto);
		}
		return returnValue;
	}

}
