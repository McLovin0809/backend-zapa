package com.ecomerce.zapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecomerce.zapa.model.MetodoPago;
import com.ecomerce.zapa.service.MetodoPagoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/metodos-pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    // GET
    @GetMapping
    @Operation(summary = "Listar todos los métodos de pago")
    public ResponseEntity<List<MetodoPago>> listar() {
        return ResponseEntity.ok(metodoPagoService.listarTodos());
    }

    // GET BY ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener método de pago por ID")
    public ResponseEntity<MetodoPago> obtener(@PathVariable Integer id) {
        MetodoPago mp = metodoPagoService.listarPorId(id);
        return (mp == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(mp);
    }

    // POST
    @PostMapping
    @Operation(summary = "Registrar nuevo método de pago")
    public ResponseEntity<MetodoPago> registrar(@RequestBody MetodoPago m) {
        return ResponseEntity.status(201).body(metodoPagoService.registrar(m));
    }

    // PUT
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar método de pago completo")
    public ResponseEntity<MetodoPago> actualizar(
            @PathVariable Integer id,
            @RequestBody MetodoPago nuevosDatos) {

        MetodoPago mp = metodoPagoService.actualizar(id, nuevosDatos);
        return (mp == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(mp);
    }

    // PATCH
    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar método de pago parcialmente")
    public ResponseEntity<MetodoPago> actualizarParcial(
            @PathVariable Integer id,
            @RequestBody MetodoPago cambios) {

        MetodoPago mp = metodoPagoService.actualizarParcial(id, cambios);
        return (mp == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(mp);
    }

    // DELETE (cascada manual)
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar método de pago con cascada")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        metodoPagoService.eliminarMetodoPago(id);
        return ResponseEntity.noContent().build();
    }
}
