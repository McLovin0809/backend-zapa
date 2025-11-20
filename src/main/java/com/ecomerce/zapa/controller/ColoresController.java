package com.ecomerce.zapa.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecomerce.zapa.model.Colores;
import com.ecomerce.zapa.service.ColoresService;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/colores-producto")
public class ColoresController {

    @Autowired
    private ColoresService coloresService;

    @GetMapping
    @Operation(summary = "listar colores-producto")
    public List<Colores> listar() {
        return coloresService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar color-producto por id")
    public Colores obtener(@PathVariable Integer id) {
        return coloresService.obtener(id);
    }

    @PostMapping
    @Operation(summary = "registrar color-producto")
    public Colores registrar(@RequestBody Colores c) {
        return coloresService.registrar(c);
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar color-producto")
    public Colores actualizar(@PathVariable Integer id, @RequestBody Colores c) {
        return coloresService.actualizar(id, c);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "actualizar parcialmente color-producto")
    public Colores actualizarParcial(@PathVariable Integer id, @RequestBody Colores c) {
        return coloresService.actualizarParcial(id, c);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "eliminar color-producto")
    public void eliminar(@PathVariable Integer id) {
        coloresService.eliminar(id);
    }
}

