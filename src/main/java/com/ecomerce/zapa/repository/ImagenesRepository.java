package com.ecomerce.zapa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.Imagenes;

@Repository
public interface ImagenesRepository extends JpaRepository<Imagenes, Integer>{

    void deleteByProducto_IdProducto(Integer idProducto);
    void deleteByImagen_IdImagen(Integer idImagen);
    List<Imagenes> findByImagen_IdImagen(Integer idImagen);

} 
