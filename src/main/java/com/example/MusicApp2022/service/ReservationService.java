package com.example.MusicApp2022.service;

import java.util.List;

import com.example.MusicApp2022.shared.dto.ReservationDto;

public interface ReservationService {

	ReservationDto getReservationById(String reservationId);

	List<ReservationDto> getReservations();

}
