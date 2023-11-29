package com.example.colaboraCityApi.entities;

import java.time.Instant;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class RefreshToken {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   @Column(nullable = false)
   private Instant expirationTime;
   @ManyToOne
   private User user;
   @Column(nullable = false, unique = true)
   private String token;
   @Column(nullable = false, unique = true)
   private String refreshToken;
}