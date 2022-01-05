package com.example.MusicApp2022.io.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.MusicApp2022.io.entity.ReservationEntity;

@Repository
public interface ReservationRepository extends PagingAndSortingRepository<ReservationEntity, Long> {

	ReservationEntity findByReservationId(String reservationId);

}
