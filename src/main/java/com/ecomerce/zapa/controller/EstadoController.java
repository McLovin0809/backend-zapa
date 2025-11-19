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

import com.ecomerce.zapa.model.Estado;
import com.ecomerce.zapa.service.EstadoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    // LISTAR TODOS
    @GetMapping
    @Operation(summary = "Listar todos los estados", 
               description = "Devuelve la lista completa de estados disponibles, como PENDIENTE, PAGADO, ENVIADO, etc.")
    public ResponseEntity<List<Estado>> listarEstados() {
        List<Estado> estados = estadoService.listarEstados();
        if (estados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estados);
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar estado por ID", 
               description = "Devuelve un estado según su identificador único.")
    public ResponseEntity<Estado> obtenerPorId(@PathVariable Integer id) {
        Estado estado = estadoService.obtenerPorId(id);
        if (estado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estado);
    }

    // REGISTRAR
    @PostMapping
    @Operation(summary = "Registrar un nuevo estado", 
               description = "Crea un estado nuevo en el sistema.")
    public ResponseEntity<Estado> registrarEstado(@RequestBody Estado estado) {
        Estado nuevo = estadoService.registrarEstado(estado);
        return ResponseEntity.status(201).body(nuevo);
    }

    // PUT
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar estado", 
               description = "Modifica completamente un estado existente según su ID.")
    public ResponseEntity<Estado> actualizarEstado(@PathVariable Integer id, @RequestBody Estado estado) {
        Estado actualizado = estadoService.actualizarEstado(id, estado);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar estado con cascada",
               description = "Elimina un estado y todas las ventas asociadas, incluyendo los productosVenta relacionados.")
    public ResponseEntity<Void> eliminarEstado(@PathVariable Integer id) {
        estadoService.eliminarEstado(id);
        return ResponseEntity.noContent().build();
    }
}

