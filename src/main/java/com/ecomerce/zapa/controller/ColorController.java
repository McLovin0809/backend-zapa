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

import com.ecomerce.zapa.model.Color;
import com.ecomerce.zapa.service.ColorService;

@RestController
@RequestMapping("/api/colores")
public class ColorController {
    @Autowired
    private ColorService colorService;

    @GetMapping
    public ResponseEntity<List<Color>> listarColores() {
        List<Color> colores = colorService.listarColores();
        if (colores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(colores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Color> obtenerPorId(@PathVariable Integer id) {
        Color color = colorService.obtenerPorId(id);
        if (color == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(color);
    }

    @PostMapping
    public ResponseEntity<Color> registrarColor(@RequestBody Color color) {
        Color nuevo = colorService.registarColor(color);
        return ResponseEntity.status(201).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Color> actualizarColor(@PathVariable Integer id, @RequestBody Color color) {
        color.setId_color(id);
        Color actualizado = colorService.actualizarColor(id, color);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarColor(@PathVariable Integer id) {
        colorService.eliminarColor(id);
        return ResponseEntity.noContent().build();
    }

    
    // personalizados
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<Color> buscarPorNombre(@PathVariable String nombre) {
        Color color = colorService.buscarPorNombre(nombre);
        if (color == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(color);
    }

    @GetMapping("/existe/{nombre}")
    public ResponseEntity<Boolean> existeColor(@PathVariable String nombre) {
        boolean existe = colorService.existeColor(nombre);
        return ResponseEntity.ok(existe);
    }

}
