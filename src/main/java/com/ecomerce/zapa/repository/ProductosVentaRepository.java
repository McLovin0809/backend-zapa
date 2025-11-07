package com.ecomerce.zapa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecomerce.zapa.model.ProductosVenta;

public interface ProductosVentaRepository extends JpaRepository<ProductosVenta, Integer> {
    // buscar x venta
    List<ProductosVenta> findByVenta_IdVenta(Integer idVenta);

    // buscar x producto cnto sde vendio en cant
    List<ProductosVenta> findByProducto_IdProducto(Integer idProducto);

    
} 
