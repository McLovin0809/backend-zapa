package com.ecomerce.zapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecomerce.zapa.model.Color;
import com.ecomerce.zapa.service.ColorService;

@RestController
@RequestMapping("/api/colores")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping
    public ResponseEntity<List<Color>> getAllColores() {
        List<Color> colores = colorService.listarColores();
        if (colores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(colores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Color> getColorById(@PathVariable Integer id) {
        return colorService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Color> createColor(@RequestBody Color color) {
        Color creado = colorService.guardarColor(color);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Color> updateColor(@PathVariable Integer id, @RequestBody Color color) {
        color.setId_color(id);
        Color actualizado = colorService.actualizarColor(color);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable Integer id) {
        if (!colorService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        colorService.eliminarColor(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Color> partialUpdateColor(@PathVariable Integer id, @RequestBody Color color) {
        color.setId_color(id);
        Color actualizado = colorService.partialUpdate(color);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
}
