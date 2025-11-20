package com.ecomerce.zapa.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecomerce.zapa.model.Imagenes;
import com.ecomerce.zapa.service.ImagenesService;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/imagenes-producto")
public class ImagenesController {

    @Autowired
    private ImagenesService imagenesService;

    @GetMapping
    @Operation(summary = "listar imagenes-producto")
    public List<Imagenes> listar() {
        return imagenesService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar imagen-producto por id")
    public Imagenes obtener(@PathVariable Integer id) {
        return imagenesService.obtener(id);
    }

    @PostMapping
    @Operation(summary = "registrar imagen-producto")
    public Imagenes registrar(@RequestBody Imagenes i) {
        return imagenesService.registrar(i);
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar imagen-producto")
    public Imagenes actualizar(@PathVariable Integer id, @RequestBody Imagenes i) {
        return imagenesService.actualizar(id, i);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "modificar parcialmente imagen-producto")
    public Imagenes actualizarParcial(@PathVariable Integer id, @RequestBody Imagenes i) {
        return imagenesService.actualizarParcial(id, i);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "eliminar imagen-producto")
    public void eliminar(@PathVariable Integer id) {
        imagenesService.eliminar(id);
    }
}


