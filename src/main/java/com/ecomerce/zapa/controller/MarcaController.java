package com.ecomerce.zapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    public ResponseEntity<List<Marca>> getAllMarcas() {
        List<Marca> marcas = marcaService.listarMarcas();
        if (marcas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(marcas);
    }

    @PostMapping
    public ResponseEntity<Marca> createMarca(@RequestBody Marca marca) {
        Marca creada = marcaService.guardarMarca(marca);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> updateMarca(@PathVariable Integer id, @RequestBody Marca marca) {
        marca.setId_marca(id);
        Marca actualizada = marcaService.guardarMarca(marca);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @PatchMapping("/{id}}")
    public ResponseEntity<Marca> updateParcialMarca(@PathVariable Integer id, @RequestBody Marca marca){
        marca.setId_marca(id);
        Marca updatedMarca = marcaService.partialUpdate(marca);
        if (updatedMarca == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMarca);
    }
}
