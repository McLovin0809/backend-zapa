package com.ecomerce.zapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecomerce.zapa.model.Talla;
import com.ecomerce.zapa.service.TallaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/tallas")
public class TallaController {

    @Autowired
    private TallaService tallaService;

    @GetMapping
    @Operation(summary = "obtener todos las tallas", description = "DEVUELVE la lista de tallas registradas.")
    public ResponseEntity<List<Talla>> getAllTallas() {
        List<Talla> lista = tallaService.listarTallas();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar talla por id", description = "devuelve una talla segn su id")
    public ResponseEntity<Talla> obtenerTallaPorId(@PathVariable Integer id) {
        Talla talla = tallaService.obtenerPorId(id);
        if (talla == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(talla);
    }

    @PostMapping
    @Operation(summary = "agregar una talla", description = "crea una talla nueva")
    public ResponseEntity<Talla> registrarTalla(@RequestBody Talla talla) {
        Talla nueva = tallaService.registarTalla(talla);
        return ResponseEntity.status(201).body(nueva);
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar talla", description = "modifica una talla existente x su id")
    public ResponseEntity<Talla> actualizarTalla(@PathVariable Integer id, @RequestBody Talla talla) {
        talla.setId_talla(id);
        Talla actualizada = tallaService.actualizarTalla(id, talla);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "eliminar talla", description = "elimina una talla x su id")
    public ResponseEntity<Void> eliminarTalla(@PathVariable Integer id) {
        tallaService.eliminarTalla(id);
        return ResponseEntity.noContent().build();
    }

    // personalizados
    @GetMapping("/buscar/{numero}")
    @Operation(summary = "buscar talla por numero", description = "permite obtener una talla específica buscando por su número exacto (por ejemplo: '38', '40', 'XL').")
    public ResponseEntity<Talla> buscarPorNumero(@PathVariable String numero) {
        Talla talla = tallaService.buscarPorNumero(numero);
        if (talla == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(talla);
    }

    @GetMapping("/existe/{numero}")
    @Operation(summary = "verificar existencia de una talla", description = "devuelve true o false segn si existe una talla registrada con el numero proporcionado.")
    public ResponseEntity<Boolean> existePorNumero(@PathVariable String numero) {
        boolean existe = tallaService.existePorNumero(numero);
        return ResponseEntity.ok(existe);
    }
}
