package com.ecomerce.zapa.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecomerce.zapa.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
    // Buscar ventas por usuario
    List<Venta> findByUsuario_IdUsuario(Integer idUsuario);

    // Buscar ventas por estado (PENDIENTE, PAGADO, etc.)
    List<Venta> findByEstado_Nombre(String estado);

    // Buscar ventas entre fechas
    List<Venta> findByFechaVentaBetween(LocalDateTime desde, LocalDateTime hasta);

    
} 
