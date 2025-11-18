package com.ecomerce.zapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.zapa.model.ProductosVenta;
import com.ecomerce.zapa.service.ProductosVentaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/productos-venta")
public class ProductosVentaController {
    @Autowired
    private ProductosVentaService productosVentaService;

    @GetMapping
    @Operation(summary = "obtener todos los productos ventas", description = "DEVUELVE la lista de productos ventas registradas.")
    public ResponseEntity<List<ProductosVenta>> listarProductosVenta() {
        List<ProductosVenta> lista = productosVentaService.listarProductosVenta();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar producto venta por id", description = "devuelve un producto venta segn su id")
    public ResponseEntity<ProductosVenta> obtenerPorId(@PathVariable Integer id) {
        ProductosVenta detalle = productosVentaService.obtenerPorId(id);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    @PostMapping
    @Operation(summary = "agregar un producto venta", description = "crea un producto venta nuevo")
    public ResponseEntity<ProductosVenta> registrarProductosVenta(@RequestBody ProductosVenta productosVenta) {
        ProductosVenta nuevo = productosVentaService.registarProductosVenta(productosVenta);
        return ResponseEntity.status(201).body(nuevo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar producto venta", description = "modifica un producto venta existente x su id")
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

    @PatchMapping("/{id}")
    @Operation(summary = "actualización parcial de detalle de venta", description = "modifica solo los campos enviados sin reemplazar todo el objeto.")
    public ResponseEntity<ProductosVenta> actualizarParcial(
            @PathVariable Integer id, @RequestBody ProductosVenta cambios) {

        ProductosVenta actualizado = productosVentaService.actualizarParcial(id, cambios);
        if (actualizado == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "eliminar producto venta", description = "elimina un producto venta x su id")
    public ResponseEntity<Void> eliminarProductosVenta(@PathVariable Integer id) {
        productosVentaService.eliminarProductosVenta(id);
        return ResponseEntity.noContent().build();
    }

    // personalizados
    @GetMapping("/venta/{idVenta}")
    @Operation(summary = "buscar productos por venta", description = "devuelve todos los productos asociados a una venta específica.")
    public ResponseEntity<List<ProductosVenta>> buscarPorVenta(@PathVariable Integer idVenta) {
        List<ProductosVenta> lista = productosVentaService.buscarPorVenta(idVenta);
        if (lista.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/producto/{idProducto}")
    @Operation(summary = "buscar detalles por producto", description = "devuelve cuantas veces y en que ventas aparece un producto especifico.")
    public ResponseEntity<List<ProductosVenta>> buscarPorProducto(@PathVariable Integer idProducto) {
        List<ProductosVenta> lista = productosVentaService.buscarPorProducto(idProducto);
        if (lista.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

}
