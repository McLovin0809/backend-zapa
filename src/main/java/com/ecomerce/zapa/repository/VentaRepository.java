package com.ecomerce.zapa.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    // buscar ventas por usuario
    List<Venta> findByUsuario_IdUsuario(Integer idUsuario);

    // buscar ventas por estado (PENDIENTE, PAGADO, etc.)
    List<Venta> findByEstado_Nombre(String estado);

    // buscar ventas entre fechas
    List<Venta> findByFechaVentaBetween(LocalDateTime desde, LocalDateTime hasta);

    void deleteByUsuario_IdUsuario(Integer idUsuario);

    void deleteByEstado_IdEstado(Integer idEstado);

    void deleteByMetodoPago_IdMetodoPago(Integer idMetodoPago)
} 
