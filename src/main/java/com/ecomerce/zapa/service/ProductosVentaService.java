package com.ecomerce.zapa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecomerce.zapa.model.ProductosVenta;
import com.ecomerce.zapa.repository.ProductosVentaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductosVentaService {

    @Autowired
    private ProductosVentaRepository productosVentaRepository;

    public List<ProductosVenta> listarProductosVenta() {
        return productosVentaRepository.findAll();
    }

    public ProductosVenta obtenerPorId(Integer id) {
        return productosVentaRepository.findById(id).orElse(null);
    }

    public ProductosVenta registarProductosVenta(ProductosVenta productosVenta) {
        return productosVentaRepository.save(productosVenta);
    }

    public ProductosVenta actualizarProductosVenta(Integer id, ProductosVenta productosVenta) {
        //  detalle existente
        ProductosVenta existingDetalle = productosVentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("detalle de productos de venta no encontrado"));

        // Actualizar solo los campos que vienen distintos (evita null)
        if (productosVenta.getVenta() != null)
            existingDetalle.setVenta(productosVenta.getVenta());
        if (productosVenta.getProducto() != null)
            existingDetalle.setProducto(productosVenta.getProducto());
        if (productosVenta.getCantidad() != null)
            existingDetalle.setCantidad(productosVenta.getCantidad());
        if (productosVenta.getSubtotal() != null)
            existingDetalle.setSubtotal(productosVenta.getSubtotal());

        return productosVentaRepository.save(existingDetalle);
    }

    public void eliminarProductosVenta(Integer id) {
        productosVentaRepository.deleteById(id);
    }

}
