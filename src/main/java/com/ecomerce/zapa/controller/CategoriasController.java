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

import com.ecomerce.zapa.model.Categorias;
import com.ecomerce.zapa.service.CategoriasService;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/categorias-producto")
public class CategoriasController {

    @Autowired
    private CategoriasService categoriasService;

    @GetMapping
    @Operation(summary = "listar categorias-producto")
    public List<Categorias> listar() {
        return categoriasService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar categoria-producto por id")
    public Categorias obtener(@PathVariable Integer id) {
        return categoriasService.obtener(id);
    }

    @PostMapping
    @Operation(summary = "registrar categoria-producto")
    public Categorias registrar(@RequestBody Categorias c) {
        return categoriasService.registrar(c);
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar categoria-producto")
    public Categorias actualizar(@PathVariable Integer id, @RequestBody Categorias c) {
        return categoriasService.actualizar(id, c);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "actualizar parcialmente categoria-producto")
    public Categorias actualizarParcial(@PathVariable Integer id, @RequestBody Categorias c) {
        return categoriasService.actualizarParcial(id, c);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "eliminar categoria-producto")
    public void eliminar(@PathVariable Integer id) {
        categoriasService.eliminar(id);
    }
}

