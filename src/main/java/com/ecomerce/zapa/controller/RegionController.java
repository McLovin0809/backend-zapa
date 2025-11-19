package com.ecomerce.zapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecomerce.zapa.model.Region;
import com.ecomerce.zapa.service.RegionService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/regiones")
public class RegionController {

    @Autowired
    private RegionService regionService;

    // GET ALL
    @GetMapping
    @Operation(summary = "Listar todas las regiones")
    public ResponseEntity<List<Region>> listar() {
        return ResponseEntity.ok(regionService.listarTodos());
    }

    // GET BY ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener región por ID")
    public ResponseEntity<Region> obtener(@PathVariable Integer id) {
        Region r = regionService.listarPorId(id);
        return (r == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(r);
    }

    // POST
    @PostMapping
    @Operation(summary = "Registrar nueva región")
    public ResponseEntity<Region> registrar(@RequestBody Region region) {
        return ResponseEntity.status(201).body(regionService.registrar(region));
    }

    // PUT
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar región completa")
    public ResponseEntity<Region> actualizar(@PathVariable Integer id, @RequestBody Region region) {
        Region r = regionService.actualizar(id, region);
        return (r == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(r);
    }

    // PATCH
    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar región parcialmente")
    public ResponseEntity<Region> actualizarParcial(
            @PathVariable Integer id, @RequestBody Region cambios) {
        Region r = regionService.actualizarParcial(id, cambios);
        return (r == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(r);
    }

    // DELETE (con cascada)
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar región con cascada manual")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        regionService.eliminarRegion(id);
        return ResponseEntity.noContent().build();
    }
}
