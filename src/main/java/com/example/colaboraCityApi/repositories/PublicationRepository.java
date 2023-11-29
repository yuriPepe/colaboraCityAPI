package com.example.colaboraCityApi.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.colaboraCityApi.entities.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long>{

    // public Publication findByDataPublicacao(LocalDateTime dataPublicacao);
     public Publication findByDataPublicacao(LocalDateTime dataPublicacao);
    
}
