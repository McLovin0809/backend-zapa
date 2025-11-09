package com.ecomerce.zapa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecomerce.zapa.model.Producto;
import com.ecomerce.zapa.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto registarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Integer id, Producto producto) {
        // Buscar producto existente
        Producto existingProducto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("producto no encontrado"));

        if (producto.getNombre() != null)
            existingProducto.setNombre(producto.getNombre());
        if (producto.getDescripcion() != null)
            existingProducto.setDescripcion(producto.getDescripcion());
        if (producto.getPrecio() != null)
            existingProducto.setPrecio(producto.getPrecio());
        if (producto.getStock() != null)
            existingProducto.setStock(producto.getStock());
        if (producto.getDescuento() != null)
            existingProducto.setDescuento(producto.getDescuento());
        if (producto.getImgPrincipal() != null)
            existingProducto.setImgPrincipal(producto.getImgPrincipal());

        // relacns con otras tablas 
        if (producto.getMarca() != null)
            existingProducto.setMarca(producto.getMarca());
        if (producto.getGenero() != null)
            existingProducto.setGenero(producto.getGenero());
        if (producto.getMaterial() != null)
            existingProducto.setMaterial(producto.getMaterial());
        if (producto.getEcofriendly() != null)
            existingProducto.setEcofriendly(producto.getEcofriendly());

        // listas
        if (producto.getCategorias() != null)
            existingProducto.setCategorias(producto.getCategorias());
        if (producto.getImagenes() != null)
            existingProducto.setImagenes(producto.getImagenes());
        if (producto.getTallas() != null)
            existingProducto.setTallas(producto.getTallas());
        if (producto.getColores() != null)
            existingProducto.setColores(producto.getColores());

        return productoRepository.save(existingProducto);
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
