package com.ecomerce.zapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    @Operation(summary = "obtener todas las marcas", description = "DEVUELVE la lista de marcas registradas.")
    public ResponseEntity<List<Marca>> ListarTodas() {
        List<Marca> marcas = marcaService.listarMarcas();
        if (marcas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar marca por id", description = "devuelve una marca segn su id")
    public ResponseEntity<Marca> obtenerPorId(@PathVariable Integer id) {
        Marca marca = marcaService.obtenerPorId(id);
        if (marca == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marca);
    }

    @PostMapping
    @Operation(summary = "agregar una marca", description = "crea una marca nueva")
    public ResponseEntity<Marca> registrarMarca(@RequestBody Marca marca) {
        Marca nueva = marcaService.registarMarca(marca);
        return ResponseEntity.status(201).body(nueva);
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar marca", description = "modifica una marca existente x su id")
    public ResponseEntity<Marca> actualizarMarca(@PathVariable Integer id, @RequestBody Marca marca) {
        marca.setIdMarca(id);
        Marca actualizada = marcaService.actualizarMarca(id, marca);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "actualización parcial de marca", description = "modifica solo los campos enviados sin reemplazar toda la marca.")
    public ResponseEntity<Marca> actualizarParcial(
            @PathVariable Integer id,
            @RequestBody Marca cambios) {

        Marca actualizada = marcaService.actualizarParcial(id, cambios);
        if (actualizada == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "eliminar marca", description = "elimina una marca x su id")
    public ResponseEntity<Void> eliminarMarca(@PathVariable Integer id) {
        marcaService.eliminarMarca(id);
        return ResponseEntity.noContent().build();
    }

    // personalizados
    @GetMapping("/buscar/exacto/{nombre}")
    @Operation(summary = "buscar marca por nombre exacto", description = "devuelve una marca cuyo nombre coincide exactamente con el valor buscado.")
    public ResponseEntity<Marca> buscarPorNombreExacto(@PathVariable String nombre) {
        Marca marca = marcaService.buscarPorNombreExacto(nombre);
        if (marca == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marca);
    }

    @GetMapping("/buscar/parcial/{nombre}")
    @Operation(summary = "buscar marcas por coincidencia parcial", description = "devuelve una lista de marcas cuyo nombre contiene el texto indicado.")
    public ResponseEntity<List<Marca>> buscarPorNombreParcial(@PathVariable String nombre) {
        List<Marca> marcas = marcaService.buscarPorNombreParcial(nombre);
        if (marcas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/existe/{nombre}")
    @Operation(summary = "verificar existencia de marca", description = "indica si existe una marca registrada con el nombre especificado.")
    public ResponseEntity<Boolean> existeMarca(@PathVariable String nombre) {
        boolean existe = marcaService.existeMarca(nombre);
        return ResponseEntity.ok(existe);
    }
}
