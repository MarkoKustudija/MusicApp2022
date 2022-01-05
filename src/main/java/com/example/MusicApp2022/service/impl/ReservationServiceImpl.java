package com.example.MusicApp2022.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MusicApp2022.io.entity.ReservationEntity;
import com.example.MusicApp2022.io.repository.ReservationRepository;
import com.example.MusicApp2022.service.ReservationService;
import com.example.MusicApp2022.shared.dto.ReservationDto;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	ReservationRepository reservationRepository;

	@Override
	public ReservationDto getReservationById(String reservationId) {
		
		ReservationDto returnValue = new ReservationDto();
		ReservationEntity reservationEntity = reservationRepository.findByReservationId(reservationId);
		
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(reservationEntity, ReservationDto.class);
				
		
		return returnValue;
	}

	@Override
	public List<ReservationDto> getReservations() {

		List<ReservationDto> returnValue = new ArrayList<>();
		Iterable<ReservationEntity> reservations = reservationRepository.findAll();
		
		for(ReservationEntity reservationEntity : reservations) {
			ReservationDto reservationDto = new ReservationDto();
			BeanUtils.copyProperties(reservationEntity, reservationDto);
			returnValue.add(reservationDto);
		}
		return returnValue;
	}
	
	

}
