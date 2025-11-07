package com.ecomerce.zapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.Talla;

@Repository
public interface TallaRepository extends JpaRepository<Talla, Integer> {
    // buscar talla 
    Talla findByNumero(String numero);

    // Verificar si ya existe una talla
    boolean existsByNumero(String numero);

    
} 
