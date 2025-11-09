package com.ecomerce.zapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.zapa.model.ProductosVenta;
import com.ecomerce.zapa.service.ProductosVentaService;

@RestController
@RequestMapping("/api/productos-venta")
public class ProductosVentaController {
    @Autowired
    private ProductosVentaService productosVentaService;

    @GetMapping
    public ResponseEntity<List<ProductosVenta>> listarProductosVenta() {
        List<ProductosVenta> lista = productosVentaService.listarProductosVenta();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductosVenta> obtenerPorId(@PathVariable Integer id) {
        ProductosVenta detalle = productosVentaService.obtenerPorId(id);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    @PostMapping
    public ResponseEntity<ProductosVenta> registrarProductosVenta(@RequestBody ProductosVenta productosVenta) {
        ProductosVenta nuevo = productosVentaService.registarProductosVenta(productosVenta);
        return ResponseEntity.status(201).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductosVenta> actualizarProductosVenta(
            @PathVariable Integer id,
            @RequestBody ProductosVenta productosVenta) {

        productosVenta.setId_productos_venta(id);
        ProductosVenta actualizado = productosVentaService.actualizarProductosVenta(id, productosVenta);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProductosVenta(@PathVariable Integer id) {
        productosVentaService.eliminarProductosVenta(id);
        return ResponseEntity.noContent().build();
    }

}
