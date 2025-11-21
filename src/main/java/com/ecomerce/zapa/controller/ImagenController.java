package com.ecomerce.zapa.controller;

import java.util.List;

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

import com.ecomerce.zapa.model.Imagen;
import com.ecomerce.zapa.service.ImagenService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/imagenes-producto")
public class ImagenController {
    @Autowired
    private ImagenService imagenService;

    @GetMapping
    @Operation(summary = "listar imagenes-producto")
    public List<Imagen> listar() {
        return imagenService.listar();
    }

     // GET BY ID
    @GetMapping("/{id}")
    public Imagen obtener(@PathVariable Integer id) {
        return imagenService.obtenerPorId(id);
    }

    // POST
    @PostMapping
    public Imagen registrar(@RequestBody Imagen imagen) {
        return imagenService.registrar(imagen);
    }

    // PUT (actualiza completo)
    @PutMapping("/{id}")
    public Imagen actualizar(@PathVariable Integer id, @RequestBody Imagen imagen) {
        return imagenService.actualizar(id, imagen);
    }

    // PATCH (actualiza solo campos enviados)
    @PatchMapping("/{id}")
    public Imagen actualizarParcial(@PathVariable Integer id, @RequestBody Imagen cambios) {
        return imagenService.actualizarParcial(id, cambios);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        imagenService.eliminar(id);
    }
}
