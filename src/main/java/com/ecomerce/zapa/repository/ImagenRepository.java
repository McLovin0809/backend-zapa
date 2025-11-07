package com.ecomerce.zapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.Imagen;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Integer>{

    
} 