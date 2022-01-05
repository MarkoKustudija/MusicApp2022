package com.example.MusicApp2022.io.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.MusicApp2022.io.entity.FestivalEntity;

@Repository
public interface FestivalRepository extends PagingAndSortingRepository<FestivalEntity, Long> {

	FestivalEntity findByFestivalId(String id);

	
}
