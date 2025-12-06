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

import com.ecomerce.zapa.model.Genero;
import com.ecomerce.zapa.service.GeneroService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    @Operation(summary = "obtener todos los géneros", description = "devuelve la lista completa de géneros registrados.")
    public ResponseEntity<List<Genero>> listarGeneros() {
        List<Genero> generos = generoService.listarGeneros();
        if (generos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(generos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar género por id", description = "devuelve un género según su id.")
    public ResponseEntity<Genero> obtenerPorId(@PathVariable Integer id) {
        Genero genero = generoService.obtenerPorId(id);
        if (genero == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(genero);
    }

    @PostMapping
    @Operation(summary = "agregar un género", description = "crea un nuevo género.")
    public ResponseEntity<Genero> registrarGenero(@Valid @RequestBody Genero genero) {
        Genero nuevo = generoService.registrarGenero(genero);
        return ResponseEntity.status(201).body(nuevo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar género", description = "modifica un género existente según su id.")
    public ResponseEntity<Genero> actualizarGenero(@PathVariable Integer id, @RequestBody Genero genero) {
        genero.setIdGenero(id);
        Genero actualizado = generoService.actualizarGenero(id, genero);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "eliminar género", description = "elimina un género según su id.")
    public ResponseEntity<Void> eliminarGenero(@PathVariable Integer id) {
        generoService.eliminarGenero(id);
        return ResponseEntity.noContent().build();
    }

    // ✔ PERSONALIZADOS
    @GetMapping("/buscar/{nombre}")
    @Operation(summary = "buscar por nombre exacto", description = "retorna un género cuyo nombre coincida exactamente.")
    public ResponseEntity<Genero> buscarPorNombre(@PathVariable String nombre) {
        Genero genero = generoService.buscarPorNombreExacto(nombre);
        if (genero == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(genero);
    }

    @GetMapping("/existe/{nombre}")
    @Operation(summary = "verificar existencia por nombre", description = "retorna true si existe un género con ese nombre.")
    public ResponseEntity<Boolean> existeGenero(@PathVariable String nombre) {
        boolean existe = generoService.existeGenero(nombre);
        return ResponseEntity.ok(existe);
    }
}
