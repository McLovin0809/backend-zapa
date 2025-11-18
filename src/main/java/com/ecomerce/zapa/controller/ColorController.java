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

import com.ecomerce.zapa.model.Color;
import com.ecomerce.zapa.service.ColorService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/colores")
public class ColorController {
    @Autowired
    private ColorService colorService;

    @GetMapping
    @Operation(summary = "obtener todas los colores", description = "DEVUELVE la lista de colores registrados.")
    public ResponseEntity<List<Color>> listarColores() {
        List<Color> colores = colorService.listarColores();
        if (colores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(colores);
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar color por id", description = "devuelve un color segn su id")
    public ResponseEntity<Color> obtenerPorId(@PathVariable Integer id) {
        Color color = colorService.obtenerPorId(id);
        if (color == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(color);
    }

    @PostMapping
    @Operation(summary = "agregar un color", description = "crea un color nuevo")
    public ResponseEntity<Color> registrarColor(@RequestBody Color color) {
        Color nuevo = colorService.registarColor(color);
        return ResponseEntity.status(201).body(nuevo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar color", description = "modifica un color existente x su id")
    public ResponseEntity<Color> actualizarColor(@PathVariable Integer id, @RequestBody Color color) {
        color.setIdColor(id);
        Color actualizado = colorService.actualizarColor(id, color);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "actualizar parcialmente un color", description = "modifica solo los campos enviados en el cuerpo sin afectar el resto.")
    public ResponseEntity<Color> actualizarParcial(
            @PathVariable Integer id,
            @RequestBody Color color) {

        Color actualizado = colorService.actualizarParcial(id, color);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "eliminar color", description = "elimina un color x su id")
    public ResponseEntity<Void> eliminarColor(@PathVariable Integer id) {
        colorService.eliminarColor(id);
        return ResponseEntity.noContent().build();
    }

    // personalizados
    @GetMapping("/buscar/{nombre}")
    @Operation(summary = "buscar color por nombre", description = "devuelve un color cuyo nombre coincide exactamente con el valor buscado.")
    public ResponseEntity<Color> buscarPorNombre(@PathVariable String nombre) {
        Color color = colorService.buscarPorNombre(nombre);
        if (color == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(color);
    }

    @GetMapping("/existe/{nombre}")
    @Operation(summary = "verificar existencia de color", description = "indica si existe un color registrado con el nombre especificado.")
    public ResponseEntity<Boolean> existeColor(@PathVariable String nombre) {
        boolean existe = colorService.existeColor(nombre);
        return ResponseEntity.ok(existe);
    }

}
