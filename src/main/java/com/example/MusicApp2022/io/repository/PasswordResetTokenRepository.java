package com.example.MusicApp2022.io.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.MusicApp2022.io.entity.PasswordResetTokenEntity;

@Repository
public interface PasswordResetTokenRepository extends PagingAndSortingRepository<PasswordResetTokenEntity, Long> {

	PasswordResetTokenEntity findByToken(String token);

}
