package com.ecomerce.zapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.Tallas;

@Repository
public interface TallasRepository extends JpaRepository<Tallas, Integer> {

    void deleteByProducto_IdProducto(Integer idProducto);
} 
