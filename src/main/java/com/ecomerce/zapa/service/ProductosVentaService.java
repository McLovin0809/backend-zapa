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
        // detalle existente
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

    public ProductosVenta actualizarParcial(Integer id, ProductosVenta cambios) {
        ProductosVenta existente = productosVentaRepository.findById(id)
                .orElse(null);

        if (existente == null)
            return null;

        if (cambios.getVenta() != null)
            existente.setVenta(cambios.getVenta());

        if (cambios.getProducto() != null)
            existente.setProducto(cambios.getProducto());

        if (cambios.getCantidad() != null)
            existente.setCantidad(cambios.getCantidad());

        if (cambios.getSubtotal() != null)
            existente.setSubtotal(cambios.getSubtotal());

        return productosVentaRepository.save(existente);
    }

    public void eliminarProductosVenta(Integer id) {
        productosVentaRepository.deleteById(id);
    }

    // personalizados
    public List<ProductosVenta> buscarPorVenta(Integer idVenta) {
        return productosVentaRepository.findByVenta_IdVenta(idVenta);
    }

    public List<ProductosVenta> buscarPorProducto(Integer idProducto) {
        return productosVentaRepository.findByProducto_IdProducto(idProducto);
    }

}
