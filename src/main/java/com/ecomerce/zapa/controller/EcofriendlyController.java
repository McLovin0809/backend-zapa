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

import com.ecomerce.zapa.model.Ecofriendly;
import com.ecomerce.zapa.service.EcofriendlyService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/ecofriendly")
public class EcofriendlyController {
    @Autowired
    private EcofriendlyService ecofriendlyService;

    @GetMapping
    @Operation(summary = "obtener todas las ecoFriendly", description = "DEVUELVE la lista de ecoFriendly registradas.")
    public ResponseEntity<List<Ecofriendly>> listarEcofriendly() {
        List<Ecofriendly> lista = ecofriendlyService.listarEcofriendly();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar ecofriendly por id", description = "devuelve un ecofriendly segn su id")
    public ResponseEntity<Ecofriendly> obtenerPorId(@PathVariable Integer id) {
        Ecofriendly eco = ecofriendlyService.obtenerPorId(id);
        if (eco == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(eco);
    }

    @PostMapping
    @Operation(summary = "agregar una ecofrinedly", description = "crea una ecofriendly nueva")
    public ResponseEntity<Ecofriendly> registrarEcofriendly(@RequestBody Ecofriendly ecofriendly) {
        Ecofriendly nuevo = ecofriendlyService.registarEcofriendly(ecofriendly);
        return ResponseEntity.status(201).body(nuevo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar ecofriendly", description = "modifica una ecofriendly existente x su id")
    public ResponseEntity<Ecofriendly> actualizarEcofriendly(@PathVariable Integer id,
            @RequestBody Ecofriendly ecofriendly) {
        ecofriendly.setId_ecofriendly(id);
        Ecofriendly actualizado = ecofriendlyService.actualizarEcofriendly(id, ecofriendly);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "eliminar ecofriendly", description = "elimina una ecofriendly x su id")
    public ResponseEntity<Void> eliminarEcofriendly(@PathVariable Integer id) {
        ecofriendlyService.eliminarEcofriendly(id);
        return ResponseEntity.noContent().build();
    }

}
