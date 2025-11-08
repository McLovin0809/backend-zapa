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
public class ProductoService {

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

    public Producto partialUpdate(Producto producto) {
        if (producto == null || producto.getId_producto() == null) {
            return null;
        }

        Producto existente = productoRepository.findById(producto.getId_producto()).orElse(null);
        if (existente != null) {
            if (producto.getNombre() != null) {
                existente.setNombre(producto.getNombre());
            }
            if (producto.getDescripcion() != null) {
                existente.setDescripcion(producto.getDescripcion());
            }
            if (producto.getPrecio() != null) {
                existente.setPrecio(producto.getPrecio());
            }
            if (producto.getStock() != null) {
                existente.setStock(producto.getStock());
            }
            if (producto.getDescuento() != null) {
                existente.setDescuento(producto.getDescuento());
            }
            if (producto.getImgPrincipal() != null) {
                existente.setImgPrincipal(producto.getImgPrincipal());
            }
            if (producto.getMarca() != null) {
                existente.setMarca(producto.getMarca());
            }
            if (producto.getGenero() != null) {
                existente.setGenero(producto.getGenero());
            }
            if (producto.getMaterial() != null) {
                existente.setMaterial(producto.getMaterial());
            }
            if (producto.getEcofriendly() != null) {
                existente.setEcofriendly(producto.getEcofriendly());
            }
            return productoRepository.save(existente);
        }
        return null;
    }
}
