package com.ecomerce.zapa.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    // buscar x nombre exacto 
    Categoria findByNombre(String nombre);

    // comprobar si existe una categoría 
    boolean existsByNombre(String nombre);

    
} 
