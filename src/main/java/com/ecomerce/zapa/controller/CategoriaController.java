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

import com.ecomerce.zapa.model.Categoria;
import com.ecomerce.zapa.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "obtener todas las categorias", description = "DEVUELVE la lista de categorias registradas.")
    public ResponseEntity<List<Categoria>> listarTodas() {
        List<Categoria> categorias = categoriaService.listarCategorias();
        if (categorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar categoria por id", description = "devuelve una categoria segn su id")
    public ResponseEntity<Categoria> obtenerPorId(@PathVariable Integer id) {
        Categoria categoria = categoriaService.obtenerPorId(id);
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    @Operation(summary = "agregar una categoria", description = "crea una categoria nueva")
    public ResponseEntity<Categoria> registrarCategoria(@RequestBody Categoria categoria) {
        Categoria nueva = categoriaService.registarCategoria(categoria);
        return ResponseEntity.status(201).body(nueva);
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar categoria", description = "modifica una categoria existente x su id")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        categoria.setId_categoria(id);
        Categoria actualizada = categoriaService.actualizarCategoria(id, categoria);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "actualizar parcialmente una categoria", description = "modifica solo los campos enviados sin afectar los demás datos existentes.")
    public ResponseEntity<Categoria> actualizarParcial(
            @PathVariable Integer id,
            @RequestBody Categoria categoria) {

        Categoria actualizado = categoriaService.actualizarParcial(id, categoria);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "eliminar categoria", description = "elimina una categoria x su id")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Integer id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    // personalizados
    @GetMapping("/buscar/{nombre}")
    @Operation(summary = "buscar categoria por nombre", description = "devuelve una categoria cuyo nombre coincide exactamente con el valor buscado.")
    public ResponseEntity<Categoria> buscarPorNombre(@PathVariable String nombre) {
        Categoria categoria = categoriaService.buscarPorNombreExacto(nombre);
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoria);
    }

    @GetMapping("/existe/{nombre}")
    @Operation(summary = "verificar existencia de categoria", description = "indica si existe una categoria registrada con el nombre especificado.")
    public ResponseEntity<Boolean> existeCategoria(@PathVariable String nombre) {
        boolean existe = categoriaService.existeCategoria(nombre);
        return ResponseEntity.ok(existe);
    }
}
