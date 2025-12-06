package com.ecomerce.zapa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.zapa.model.Direccion;
import com.ecomerce.zapa.service.DireccionService;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @GetMapping
    @Operation(summary = "listar direcciones", description = "devuelve todas las direcciones registradas")
    public List<Direccion> listar() {
        return direccionService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "obtener direccion", description = "busca una direccion por su id")
    public Direccion obtener(@PathVariable Integer id) {
        return direccionService.obtener(id);
    }

    @PostMapping
    @Operation(summary = "crear direccion", description = "registra una nueva direccion")
    public Direccion registrar(@RequestBody Direccion d) {
        return direccionService.registrar(d);
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar direccion", description = "actualiza completamente una direccion")
    public Direccion actualizar(@PathVariable Integer id, @RequestBody Direccion d) {
        return direccionService.actualizar(id, d);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "actualizar parcialmente", description = "modifica solo algunos campos de una direccion")
    public Direccion actualizarParcial(@PathVariable Integer id, @RequestBody Direccion d) {
        return direccionService.actualizarParcial(id, d);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "eliminar direccion", description = "elimina una direccion por id")
    public void eliminar(@PathVariable Integer id) {
        direccionService.eliminar(id);
    }
}
