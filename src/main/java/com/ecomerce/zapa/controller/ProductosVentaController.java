package com.ecomerce.zapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecomerce.zapa.model.ProductosVenta;
import com.ecomerce.zapa.service.ProductosVentaService;

@RestController
@RequestMapping("/api/productos-venta")
public class ProductosVentaController {

    @Autowired
    private ProductosVentaService productosVentaService;

    @GetMapping
    public ResponseEntity<List<ProductosVenta>> getAllProductosVenta() {
        List<ProductosVenta> lista = productosVentaService.listarProductosVenta();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductosVenta> getProductosVentaById(@PathVariable Integer id) {
        return productosVentaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductosVenta> createProductosVenta(@RequestBody ProductosVenta productosVenta) {
        ProductosVenta creado = productosVentaService.guardarProductosVenta(productosVenta);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductosVenta> updateProductosVenta(@PathVariable Integer id, @RequestBody ProductosVenta productosVenta) {
        productosVenta.setId_productos_venta(id);
        ProductosVenta actualizado = productosVentaService.actualizarProductosVenta(productosVenta);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductosVenta(@PathVariable Integer id) {
        if (!productosVentaService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        productosVentaService.eliminarProductosVenta(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductosVenta> updateParcialProductosVenta(@PathVariable Integer id, @RequestBody ProductosVenta productosVenta) {
        productosVenta.setId_productos_venta(id);
        ProductosVenta actualizado = productosVentaService.partialUpdate(productosVenta);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
}
