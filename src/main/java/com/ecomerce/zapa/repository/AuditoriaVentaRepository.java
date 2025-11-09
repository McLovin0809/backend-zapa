package com.ecomerce.zapa.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.AuditoriaVenta;

@Repository
public interface AuditoriaVentaRepository extends JpaRepository<AuditoriaVenta, Integer> {

    List<AuditoriaVenta> findByFechaRegistroBetween(LocalDateTime inicio, LocalDateTime fin);


    List<AuditoriaVenta> findByVenta_IdVenta(Integer idVenta);

    
} 
