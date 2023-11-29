package com.example.colaboraCityApi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.example.colaboraCityApi.entities.RefreshToken;
import com.example.colaboraCityApi.entities.User;
import com.example.colaboraCityApi.entities.Citizen;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
   Optional<RefreshToken> findByToken(String token);
   Optional<RefreshToken> findByRefreshToken(String refreshToken);
   @Modifying
   int deleteByUser(User cidadao);
}