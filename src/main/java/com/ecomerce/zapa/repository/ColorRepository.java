package com.ecomerce.zapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
    // Buscar color exacto
    Color findByNombre(String nombre);

    // Verificar si ya existe un color
    boolean existsByNombre(String nombre);

    
} 