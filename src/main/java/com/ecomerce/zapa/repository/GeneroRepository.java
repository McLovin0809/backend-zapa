package com.ecomerce.zapa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {

    // buscar exacto
    Genero findByNombre(String nombre);

    // buscar parcial
    List<Genero> findByNombreContainingIgnoreCase(String nombre);

    // validar existencia
    boolean existsByNombre(String nombre);

} 