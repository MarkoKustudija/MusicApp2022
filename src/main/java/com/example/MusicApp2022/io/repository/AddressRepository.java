package com.example.MusicApp2022.io.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.MusicApp2022.io.entity.AddressEntity;
import com.example.MusicApp2022.io.entity.UserEntity;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<AddressEntity, Long> {

	Iterable<AddressEntity> findAllByUserDetails(UserEntity userEntity);

	AddressEntity findByAddressId(String addressId);

}
