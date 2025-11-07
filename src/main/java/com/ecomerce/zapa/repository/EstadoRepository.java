package com.ecomerce.zapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    
} 
