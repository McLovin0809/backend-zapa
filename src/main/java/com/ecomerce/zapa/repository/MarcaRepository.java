package com.ecomerce.zapa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecomerce.zapa.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {
    // buscar x nombre exacto
    Marca findByNombre(String nombre);

    // buscar x parte del nombre ad = Adidas)
    List<Marca> findByNombreContainingIgnoreCase(String nombre);

    // Saber si una marca ya existe
    boolean existsByNombre(String nombre);

    
} 
