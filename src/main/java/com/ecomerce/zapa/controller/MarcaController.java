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

import com.ecomerce.zapa.model.Marca;
import com.ecomerce.zapa.service.MarcaService;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<Marca>> ListarTodas() {
        List<Marca> marcas = marcaService.listarMarcas();
        if (marcas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> obtenerPorId(@PathVariable Integer id) {
        Marca marca = marcaService.obtenerPorId(id);
        if (marca == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marca);
    }

    @PostMapping
    public ResponseEntity<Marca> registrarMarca(@RequestBody Marca marca) {
        Marca nueva = marcaService.registarMarca(marca);
        return ResponseEntity.status(201).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> actualizarMarca(@PathVariable Integer id, @RequestBody Marca marca) {
        marca.setId_marca(id);
        Marca actualizada = marcaService.actualizarMarca(id,marca);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMarca(@PathVariable Integer id) {
        marcaService.eliminarMarca(id);
        return ResponseEntity.noContent().build();
    }

    // personalizados
    @GetMapping("/buscar/exacto/{nombre}")
    public ResponseEntity<Marca> buscarPorNombreExacto(@PathVariable String nombre) {
        Marca marca = marcaService.buscarPorNombreExacto(nombre);
        if (marca == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marca);
    }

    @GetMapping("/buscar/parcial/{nombre}")
    public ResponseEntity<List<Marca>> buscarPorNombreParcial(@PathVariable String nombre) {
        List<Marca> marcas = marcaService.buscarPorNombreParcial(nombre);
        if (marcas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/existe/{nombre}")
    public ResponseEntity<Boolean> existeMarca(@PathVariable String nombre) {
        boolean existe = marcaService.existeMarca(nombre);
        return ResponseEntity.ok(existe);
    }
}
