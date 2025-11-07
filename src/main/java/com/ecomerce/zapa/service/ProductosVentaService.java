package com.ecomerce.zapa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.Producto;
import com.ecomerce.zapa.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductosVentaService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Integer id) {
        return productoRepository.findById(id);
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    public List<Producto> buscarPorMarca(String nombreMarca) {
        return productoRepository.findByMarca_Nombre(nombreMarca);
    }

    public List<Producto> buscarPorRangoDePrecio(Double min, Double max) {
        return productoRepository.findByPrecioBetween(min, max);
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        return productoRepository.findByCategorias_Categoria_Nombre(categoria);
    }

    public List<Producto> buscarPorGenero(String genero) {
        return productoRepository.findByGenero_Nombre(genero);
    }

}
