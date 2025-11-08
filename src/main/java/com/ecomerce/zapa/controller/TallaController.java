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
    public ResponseEntity<Talla> getTallaById(@PathVariable Integer id) {
        return tallaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Talla> createTalla(@RequestBody Talla talla) {
        Talla creada = tallaService.guardarTalla(talla);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Talla> updateTalla(@PathVariable Integer id, @RequestBody Talla talla) {
        talla.setId_talla(id);
        Talla actualizada = tallaService.actualizarTalla(talla);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTalla(@PathVariable Integer id) {
        if (!tallaService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tallaService.eliminarTalla(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Talla> updateParcialTalla(@PathVariable Integer id, @RequestBody Talla talla) {
        talla.setId_talla(id);
        Talla actualizada = tallaService.partialUpdate(talla);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }
}
