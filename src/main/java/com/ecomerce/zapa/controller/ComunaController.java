package com.ecomerce.zapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecomerce.zapa.model.Comuna;
import com.ecomerce.zapa.service.ComunaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/comunas")
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    // GET ALL
    @GetMapping
    @Operation(summary = "Listar todas las comunas")
    public ResponseEntity<List<Comuna>> listar() {
        return ResponseEntity.ok(comunaService.listarTodos());
    }

    // GET BY ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener comuna por ID")
    public ResponseEntity<Comuna> obtener(@PathVariable Integer id) {
        Comuna c = comunaService.listarPorId(id);
        return (c == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
    }

    // POST
    @PostMapping
    @Operation(summary = "Registrar nueva comuna")
    public ResponseEntity<Comuna> registrar(@RequestBody Comuna comuna) {
        return ResponseEntity.status(201).body(comunaService.registrar(comuna));
    }

    // PUT
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar comuna completa")
    public ResponseEntity<Comuna> actualizar(
            @PathVariable Integer id,
            @RequestBody Comuna comuna) {

        Comuna c = comunaService.actualizar(id, comuna);
        return (c == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
    }

    // PATCH
    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar comuna parcialmente")
    public ResponseEntity<Comuna> actualizarParcial(
            @PathVariable Integer id,
            @RequestBody Comuna cambios) {

        Comuna c = comunaService.actualizarParcial(id, cambios);
        return (c == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
    }

    // DELETE (cascada manual)
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar comuna con cascada manual")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        comunaService.eliminarComuna(id);
        return ResponseEntity.noContent().build();
    }
}
