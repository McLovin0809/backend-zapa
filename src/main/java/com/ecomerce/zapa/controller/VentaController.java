package com.ecomerce.zapa.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.zapa.model.Venta;
import com.ecomerce.zapa.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping
    @Operation(summary = "obtener todas las ventas", description = "DEVUELVE la lista de ventas registradas.")
    public ResponseEntity<List<Venta>> listartodas() {
        List<Venta> ventas = ventaService.listarVentas();
        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar venta por id", description = "devuelve una venta segn su id")
    public ResponseEntity<Venta> obtenerPorId(@PathVariable Integer id) {
        Venta venta = ventaService.obtenerPorId(id);
        if (venta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(venta);
    }

    @PostMapping
    @Operation(summary = "agregar una venta", description = "crea una venta nueva")
    public ResponseEntity<Venta> registrarVenta(@RequestBody Venta venta) {
        Venta nueva = ventaService.registrarVenta(venta);
        return ResponseEntity.status(201).body(nueva);
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar venta", description = "modifica una venta existente x su id")
    public ResponseEntity<Venta> actualizarVenta(@PathVariable Integer id, @RequestBody Venta venta) {
        venta.setIdVenta(id);
        Venta actualizada = ventaService.actualizarVenta(id, venta);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "eliminar venta", description = "elimina una venta x su id")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Integer id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }

    // personalizados
    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "buscar ventas por usuario", description = "retorna todas las ventas asociadas a un usuario especifico mediante su id.")
    public ResponseEntity<List<Venta>> buscarPorUsuario(@PathVariable Integer idUsuario) {
        List<Venta> ventas = ventaService.buscarPorUsuario(idUsuario);
        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/estado/{nombreEstado}")
    @Operation(summary = "buscar ventas por estado", description = "filtra y devuelve todas las ventas que se encuentren en un estado específico, como 'pendiente', 'pagado' o 'en ruta'.")
    public ResponseEntity<List<Venta>> buscarPorEstado(@PathVariable String nombreEstado) {
        List<Venta> ventas = ventaService.buscarPorEstado(nombreEstado);
        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/fecha")
    @Operation(summary = "buscar ventas por rango de fechas", description = "permite obtener las ventas registradas entre dos fechas dadas")
    public ResponseEntity<List<Venta>> buscarPorRangoFechas(
            @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta) {

        List<Venta> ventas = ventaService.buscarPorRangoDeFechas(desde, hasta);
        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }

}
