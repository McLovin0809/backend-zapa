package com.ecomerce.zapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecomerce.zapa.model.Talla;

public interface TallaRepository extends JpaRepository<Talla, Integer> {
    // buscar talla 
    Talla findByNumero(String numero);

    // Verificar si ya existe una talla
    boolean existsByNumero(String numero);

    
} 
