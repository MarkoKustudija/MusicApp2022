package com.example.MusicApp2022.service;

import java.util.List;

import com.example.MusicApp2022.shared.dto.FestivalDto;

public interface FestivalService {
	
	FestivalDto create(FestivalDto festivalDto);

	FestivalDto getFestivalById(String id);

	List<FestivalDto> getFestivals(int page, int limit);
		
	FestivalDto update(FestivalDto festivalDto, String festivalId);

	void delete(String id);

	

	


}
