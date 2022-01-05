package com.example.MusicApp2022.io.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.MusicApp2022.io.entity.CityEntity;

@Repository
public interface CityRepository extends PagingAndSortingRepository<CityEntity, Long> {

}
