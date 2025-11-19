package com.ecomerce.zapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.Comuna;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer> {

    void deleteByRegion_IdRegion(Integer idRegion);
} 
