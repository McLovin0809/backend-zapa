package com.ecomerce.zapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.Direccion;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Integer> {

    
}
