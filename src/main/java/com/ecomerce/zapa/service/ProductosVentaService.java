package com.ecomerce.zapa.service;

import java.util.List;
import java.util.Optional;

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

    public Optional<ProductosVenta> obtenerPorId(Integer id) {
        return productosVentaRepository.findById(id);
    }

    public ProductosVenta guardarProductosVenta(ProductosVenta productosVenta) {
        return productosVentaRepository.save(productosVenta);
    }

    public ProductosVenta actualizarProductosVenta(ProductosVenta productosVenta) {
        return productosVentaRepository.save(productosVenta);
    }

    public void eliminarProductosVenta(Integer id) {
        productosVentaRepository.deleteById(id);
    }

    public ProductosVenta partialUpdate(ProductosVenta productosVenta) {
        ProductosVenta existente = productosVentaRepository.findById(productosVenta.getId_productos_venta()).orElse(null);
        if (existente != null) {
            if (productosVenta.getVenta() != null) {
                existente.setVenta(productosVenta.getVenta());
            }
            if (productosVenta.getProducto() != null) {
                existente.setProducto(productosVenta.getProducto());
            }
            if (productosVenta.getCantidad() != null) {
                existente.setCantidad(productosVenta.getCantidad());
            }
            if (productosVenta.getSubtotal() != null) {
                existente.setSubtotal(productosVenta.getSubtotal());
            }
            return productosVentaRepository.save(existente);
        }
        return null;
    }
}
