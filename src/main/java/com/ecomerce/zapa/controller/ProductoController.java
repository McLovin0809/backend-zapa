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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.zapa.model.Producto;
import com.ecomerce.zapa.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(summary = "obtener todos los productos", description = "DEVUELVE la lista de productos registrados.")
    public ResponseEntity<List<Producto>> listarTodos() {
        List<Producto> productos = productoService.listarProductos();
        //if (productos.isEmpty()) {
            //return ResponseEntity.noContent().build();
        //}
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar producto por id", description = "devuelve un producto segn su id")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Integer id) {
        Producto producto = productoService.obtenerPorId(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }



    @PostMapping
    @Operation(summary = "agregar un producto", description = "crea un producto nuevo")
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto nuevo = productoService.registarProducto(producto);
        return ResponseEntity.status(201).body(nuevo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar producto", description = "modifica un producto existente x su id")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        producto.setIdProducto(id);
        Producto actualizado = productoService.actualizarProducto(id, producto);
        return ResponseEntity.ok(actualizado);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "actualización parcial del producto", description = "permite modificar solo algunos campos del producto sin reemplazarlo completo.")
    public ResponseEntity<Producto> actualizarParcial(
            @PathVariable Integer id,
            @RequestBody Producto cambios) {

        Producto actualizado = productoService.actualizarParcial(id, cambios);
        if (actualizado == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "eliminar producto", description = "elimina un producto x su id")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    // personalizados
    @GetMapping("/marca/{nombreMarca}")
    @Operation(summary = "buscar productos por marca", description = "devuelve todos los productos que pertenecen a una marca específica.")
    public ResponseEntity<List<Producto>> buscarPorMarca(@PathVariable String nombreMarca) {
        List<Producto> productos = productoService.buscarPorMarca(nombreMarca);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/categoria/{nombreCategoria}")
    @Operation(summary = "buscar productos por categoría", description = "devuelve los productos asociados a una categoría específica.")
    public ResponseEntity<List<Producto>> buscarPorCategoria(@PathVariable String nombreCategoria) {
        List<Producto> productos = productoService.buscarPorCategoria(nombreCategoria);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/genero/{nombreGenero}")
    @Operation(summary = "buscar productos por género", description = "devuelve los productos filtrados según el género ingresado.")
    public ResponseEntity<List<Producto>> buscarPorGenero(@PathVariable String nombreGenero) {
        List<Producto> productos = productoService.buscarPorGenero(nombreGenero);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/precio")
    @Operation(summary = "buscar productos por rango de precio", description = "devuelve los productos cuyo precio esté entre el mínimo y máximo.")
    public ResponseEntity<List<Producto>> buscarPorRangoDePrecio(
            @RequestParam Double min,
            @RequestParam Double max) {
        List<Producto> productos = productoService.buscarPorRangoDePrecio(min, max);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/descuento/{minimo}")
    @Operation(summary = "buscar productos con descuento", description = "devuelve los productos que tienen un descuento mayor al valor indicado.")
    public ResponseEntity<List<Producto>> buscarConDescuento(@PathVariable Double minimo) {
        List<Producto> productos = productoService.buscarConDescuento(minimo);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }
}
