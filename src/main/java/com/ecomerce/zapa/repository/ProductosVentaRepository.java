package com.ecomerce.zapa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.ProductosVenta;

@Repository
public interface ProductosVentaRepository extends JpaRepository<ProductosVenta, Integer> {
    // buscar x venta
    List<ProductosVenta> findByVenta_IdVenta(Integer idVenta);

    // buscar x producto cnto sde vendio en cant
    List<ProductosVenta> findByProducto_IdProducto(Integer idProducto);

    
} 
