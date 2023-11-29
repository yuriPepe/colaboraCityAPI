package com.example.colaboraCityApi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.colaboraCityApi.entities.Citizen;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {
    
    public List<Citizen> findByName(String name);

    public Citizen findByEmail(String username);

    

}
