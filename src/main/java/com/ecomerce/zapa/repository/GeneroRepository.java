package com.ecomerce.zapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {

    
} 