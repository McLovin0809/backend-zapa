package com.ecomerce.zapa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.Comuna;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer> {

    // obtener comunas por region, necesario para loops de cascada
    List<Comuna> findByRegion_IdRegion(Integer idRegion);

    // eliminar comunas por regions
    void deleteByRegion_IdRegion(Integer idRegion);
} 
