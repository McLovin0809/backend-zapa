package com.ecomerce.zapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecomerce.zapa.model.Color;

public interface ColorRepository extends JpaRepository<Color, Integer> {
    // Buscar color exacto
    Color findByNombre(String nombre);
    
    // Verificar si ya existe un color
    boolean existsByNombre(String nombre);

    
} 