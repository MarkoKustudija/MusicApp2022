package com.example.MusicApp2022.ui.controller;


import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MusicApp2022.service.ReservationService;
import com.example.MusicApp2022.shared.dto.ReservationDto;
import com.example.MusicApp2022.ui.response.model.ReservationRest;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {
	
	
	@Autowired
	ReservationService reservationService;
	

	@GetMapping(path = "/{id}")
	public ReservationRest getReservation(@PathVariable String id) {
		
		ReservationRest returnValue = new ReservationRest();
		ReservationDto reservationDto = reservationService.getReservationById(id);
		
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(reservationDto, ReservationRest.class);
		
		return returnValue;
		
	}
	
	@GetMapping
	public List<ReservationRest> getReservations(){
		
		List<ReservationRest> returnValue = new ArrayList<>();
		
		List<ReservationDto> reservations = reservationService.getReservations();
		
		for(ReservationDto reservationDto : reservations) {
			ReservationRest reservationRest = new ReservationRest();
			BeanUtils.copyProperties(reservationDto, reservationRest);
			returnValue.add(reservationRest);
		}
		
		return returnValue;
		
		
		
	}
	
	

}
