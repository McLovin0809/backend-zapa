package com.ecomerce.zapa.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.AuditoriaProducto;

@Repository
public interface AuditoriaProductoRepository extends JpaRepository<AuditoriaProducto, Integer> {
    // historial de cambios de un producto
    List<AuditoriaProducto> findByProducto_IdProducto(Integer idProducto);

    // historial por usuario admin que hizo los cambios
    List<AuditoriaProducto> findByUsuario_Nombre(String nombreAdmin);

    // liltrar por rango de fechas
    List<AuditoriaProducto> findByFechaCambioBetween(LocalDateTime inicio, LocalDateTime fin);

    
}   


