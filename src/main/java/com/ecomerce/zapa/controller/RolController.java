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

import com.ecomerce.zapa.model.Rol;
import com.ecomerce.zapa.service.RolService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/roles")
public class RolController {
    @Autowired
    private RolService rolService;

    @GetMapping
    @Operation(summary = "obtener todos los roles", description = "devuelve la lista completa de roles registrados.")
    public ResponseEntity<List<Rol>> listarTodos() {
        List<Rol> roles = rolService.listarTodos();
        if (roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar rol por ID", description = "devuelve un rol segun su id.")
    public ResponseEntity<Rol> obtenerPorId(@PathVariable Integer id) {
        Rol rol = rolService.listraPorId(id);
        if (rol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rol);
    }

    @PostMapping
    @Operation(summary = "Registrar rol", description = "crea un nuevo rol")
    public ResponseEntity<Rol> registrar(@RequestBody Rol rol) {
        Rol creado = rolService.registrar(rol);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar rol", description = "modifica un rol existente segun su ID.")
    public ResponseEntity<Rol> actualizar(@PathVariable Integer id, @RequestBody Rol rol) {
        Rol actualizado = rolService.actualizar(id, rol);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "eliminar rol", description = "elimina un rol segun su ID.")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        rolService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
