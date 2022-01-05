package com.example.MusicApp2022.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.MusicApp2022.io.entity.FestivalEntity;
import com.example.MusicApp2022.io.repository.FestivalRepository;
import com.example.MusicApp2022.service.FestivalService;
import com.example.MusicApp2022.shared.dto.FestivalDto;
import com.example.MusicApp2022.shared.dto.ReservationDto;
import com.example.MusicApp2022.shared.utils.Utils;

@Service
public class FestivalServiceImpl implements FestivalService {
	
	@Autowired
	FestivalRepository festivalRepository;
	
	@Autowired
	Utils utils;
	
	@Override
	public FestivalDto create(FestivalDto festivalDto) {
		
		FestivalDto returnValue = new FestivalDto();
		ModelMapper modelMapper = new ModelMapper();
		
		for(int i=0; i<festivalDto.getReservations().size();i++) {
			ReservationDto reservationDto = festivalDto.getReservations().get(i);
			reservationDto.setFestivalDetails(festivalDto);
			reservationDto.setReservationId(utils.generateReservationId(30));
			festivalDto.getReservations().set(i, reservationDto);
		}
		
		
		FestivalEntity festivalEntity = modelMapper.map(festivalDto, FestivalEntity.class);
		
		String publicFestivalId = (utils.generateFestivalId(30));
		festivalEntity.setFestivalId(publicFestivalId);
//		festivalEntity.setCityDetails(cityDto);
		
		FestivalEntity createdFestival = festivalRepository.save(festivalEntity);
		
		returnValue = modelMapper.map(createdFestival, FestivalDto.class);
		
		return returnValue;
	}

	

	@Override
	public FestivalDto getFestivalById(String id) {
		
		FestivalDto returnValue = new FestivalDto();
		FestivalEntity festivalEntity = festivalRepository.findByFestivalId(id);
		
		if(festivalEntity == null) return returnValue;
		
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(festivalEntity, FestivalDto.class);
		
		return returnValue;
	}

	@Override
	public List<FestivalDto> getFestivals(int page, int limit) {

		List<FestivalDto> returnValue = new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<FestivalEntity> festivalPages = festivalRepository.findAll(pageableRequest);
		List<FestivalEntity> festivals = festivalPages.getContent();
		
		for(FestivalEntity festivalEntity : festivals) {
			FestivalDto festivalDto = new FestivalDto();
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.map(festivalEntity, festivalDto);
			returnValue.add(festivalDto);
		}
		return returnValue;
	}
	
	@Override
	public FestivalDto update(FestivalDto festivalDto, String festivalId) {
		
		FestivalDto returnValue = new FestivalDto();
		FestivalEntity festivalEntity = festivalRepository.findByFestivalId(festivalId);
		
		if(festivalEntity == null) 
			throw new RuntimeException("Record not found!");
		
		festivalEntity.setFestivalName(festivalDto.getFestivalName());
		festivalEntity.setDateStarts(festivalDto.getDateStarts());
		festivalEntity.setDateEnds(festivalDto.getDateEnds());
		festivalEntity.setTicketPrice(festivalDto.getTicketPrice());
		festivalEntity.setNumberOfTickets(festivalDto.getNumberOfTickets());
		
		FestivalEntity updatedFestival = festivalRepository.save(festivalEntity);
		
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(updatedFestival, FestivalDto.class);
	
		return returnValue;
	}

	@Override
	public void delete(String id) {

	  FestivalEntity festivalEntity = festivalRepository.findByFestivalId(id);
	  
	  if(festivalEntity == null)
		  throw new RuntimeException("Record not found!");
		  
      festivalRepository.delete(festivalEntity);		
		
	}

	

}
