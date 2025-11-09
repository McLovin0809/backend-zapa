package com.ecomerce.zapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecomerce.zapa.model.Talla;
import com.ecomerce.zapa.service.TallaService;

@RestController
@RequestMapping("/api/tallas")
public class TallaController {

    @Autowired
    private TallaService tallaService;

    @GetMapping
    public ResponseEntity<List<Talla>> getAllTallas() {
        List<Talla> lista = tallaService.listarTallas();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Talla> obtenerTallaPorId(@PathVariable Integer id) {
        Talla talla = tallaService.obtenerPorId(id);
        if (talla == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(talla);
    }

    @PostMapping
    public ResponseEntity<Talla> registrarTalla(@RequestBody Talla talla) {
        Talla nueva = tallaService.registarTalla(talla);
        return ResponseEntity.status(201).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Talla> actualizarTalla(@PathVariable Integer id, @RequestBody Talla talla) {
        talla.setId_talla(id);
        Talla actualizada = tallaService.actualizarTalla(id, talla);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTalla(@PathVariable Integer id) {
        tallaService.eliminarTalla(id);
        return ResponseEntity.noContent().build();
    }

    // personalizados
    @GetMapping("/buscar/{numero}")
    public ResponseEntity<Talla> buscarPorNumero(@PathVariable String numero) {
        Talla talla = tallaService.buscarPorNumero(numero);
        if (talla == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(talla);
    }

    @GetMapping("/existe/{numero}")
    public ResponseEntity<Boolean> existePorNumero(@PathVariable String numero) {
        boolean existe = tallaService.existePorNumero(numero);
        return ResponseEntity.ok(existe);
    }
}
