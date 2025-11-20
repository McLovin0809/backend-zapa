package com.ecomerce.zapa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    // buscar exacto
    Material findByNombre(String nombre);

    // buscar parcial
    List<Material> findByNombreContainingIgnoreCase(String nombre);

    // validar existencia
    boolean existsByNombre(String nombre);

    
} 