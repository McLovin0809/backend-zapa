package com.ecomerce.zapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.Categorias;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Integer> {

    void deleteByProducto_IdProducto(Integer idProducto);
} 
