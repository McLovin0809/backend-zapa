package com.ecomerce.zapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecomerce.zapa.model.Material;
import com.ecomerce.zapa.service.MaterialService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping
    @Operation(summary = "obtener todos los materiales",
               description = "devuelve la lista completa de materiales registrados.")
    public ResponseEntity<List<Material>> listarMateriales() {
        List<Material> materiales = materialService.listarMateriales();
        if (materiales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(materiales);
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar material por id",
               description = "devuelve un material según su id.")
    public ResponseEntity<Material> obtenerPorId(@PathVariable Integer id) {
        Material material = materialService.obtenerPorId(id);
        if (material == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(material);
    }

    @PostMapping
    @Operation(summary = "agregar un material",
               description = "crea un nuevo material.")
    public ResponseEntity<Material> registrarMaterial(@RequestBody Material material) {
        Material nuevo = materialService.registrarMaterial(material);
        return ResponseEntity.status(201).body(nuevo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar material",
               description = "modifica un material existente según su id.")
    public ResponseEntity<Material> actualizarMaterial(@PathVariable Integer id, @RequestBody Material material) {
        material.setIdMaterial(id);
        Material actualizado = materialService.actualizarMaterial(id, material);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "eliminar material",
               description = "elimina un material según su id.")
    public ResponseEntity<Void> eliminarMaterial(@PathVariable Integer id) {
        materialService.eliminarMaterial(id);
        return ResponseEntity.noContent().build();
    }

    // ✔ PERSONALIZADOS
    @GetMapping("/buscar/{nombre}")
    @Operation(summary = "buscar por nombre exacto",
               description = "retorna un material cuyo nombre coincida exactamente.")
    public ResponseEntity<Material> buscarPorNombre(@PathVariable String nombre) {
        Material material = materialService.buscarPorNombreExacto(nombre);
        if (material == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(material);
    }

    @GetMapping("/existe/{nombre}")
    @Operation(summary = "verificar existencia por nombre",
               description = "retorna true si existe un material con ese nombre.")
    public ResponseEntity<Boolean> existeMaterial(@PathVariable String nombre) {
        boolean existe = materialService.existeMaterial(nombre);
        return ResponseEntity.ok(existe);
    }
}
