package com.ecomerce.zapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecomerce.zapa.model.Ecofriendly;
import com.ecomerce.zapa.service.EcofriendlyService;

@RestController
@RequestMapping("/api/ecofriendly")
public class EcofriendlyController {

    @Autowired
    private EcofriendlyService ecofriendlyService;

    @GetMapping
    public ResponseEntity<List<Ecofriendly>> getAllEcofriendly() {
        List<Ecofriendly> lista = ecofriendlyService.listarEcofriendly();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ecofriendly> getEcofriendlyById(@PathVariable Integer id) {
        return ecofriendlyService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ecofriendly> createEcofriendly(@RequestBody Ecofriendly ecofriendly) {
        Ecofriendly creado = ecofriendlyService.guardarEcofriendly(ecofriendly);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ecofriendly> updateEcofriendly(@PathVariable Integer id, @RequestBody Ecofriendly ecofriendly) {
        ecofriendly.setId_ecofriendly(id);
        Ecofriendly actualizado = ecofriendlyService.actualizarEcofriendly(ecofriendly);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEcofriendly(@PathVariable Integer id) {
        if (!ecofriendlyService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ecofriendlyService.eliminarEcofriendly(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ecofriendly> partialUpdateEcofriendly(@PathVariable Integer id, @RequestBody Ecofriendly ecofriendly) {
        ecofriendly.setId_ecofriendly(id);
        Ecofriendly actualizado = ecofriendlyService.partialUpdate(ecofriendly);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
}
