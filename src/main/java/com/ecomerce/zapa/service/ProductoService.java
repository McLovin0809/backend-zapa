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
public class ProductoService {
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

    public void eliminarProductosVenta(Integer id) {
        productosVentaRepository.deleteById(id);
    }

}
