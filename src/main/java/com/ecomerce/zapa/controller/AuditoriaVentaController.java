package com.ecomerce.zapa.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.zapa.model.AuditoriaVenta;
import com.ecomerce.zapa.service.AuditoriaVentaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/auditorias-venta")
public class AuditoriaVentaController {

    @Autowired
    private AuditoriaVentaService auditoriaVentaService;

    @GetMapping
    @Operation(summary = "obtener todas las auditorias ventas", description = "DEVUELVE la lista de auditorias ventas registradas.")
    public ResponseEntity<List<AuditoriaVenta>> listarAuditoriasVentas() {
        List<AuditoriaVenta> auditorias = auditoriaVentaService.listarAuditoriasVentas();
        if (auditorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar auditoria venta por id", description = "devuelve una auditoria venta segn su id")
    public ResponseEntity<AuditoriaVenta> obtenerAuditoriaPorId(@PathVariable Integer id) {
        try {
            AuditoriaVenta auditoria = auditoriaVentaService.obtenerPorId(id);
            return ResponseEntity.ok(auditoria);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "agregar una auditoria venta", description = "crea una auditoria venta nueva")
    public ResponseEntity<AuditoriaVenta> registrarAuditoriaVenta(@RequestBody AuditoriaVenta auditoriaVenta) {
        AuditoriaVenta nueva = auditoriaVentaService.registrarAuditoriaVenta(auditoriaVenta);
        return ResponseEntity.status(201).body(nueva);
    }

    // agrega de forma auto una nueva versión vinculada a una venta
    @PostMapping("/registrar/{idVenta}")
    @Operation(summary = "registrar nueva version de auditoria", description = "crea automaticamente una nueva auditoria vinculada a la venta indicada, guardando total y ganancia actual.")
    public ResponseEntity<AuditoriaVenta> registrarNuevaVersion(
            @PathVariable Integer idVenta,
            @RequestParam Double totalVenta,
            @RequestParam Double ganancia) {

        AuditoriaVenta nueva = auditoriaVentaService.registrarNuevaVersion(idVenta, totalVenta, ganancia);
        return ResponseEntity.status(201).body(nueva);
    }

    // personalizados
    @GetMapping("/filtrar")
    @Operation(summary = "filtrar auditorias por fechas", description = "devuelve todas las auditorias creadas entre las fechas especificadas.")
    public ResponseEntity<List<AuditoriaVenta>> filtrarPorFechas(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {

        List<AuditoriaVenta> auditorias = auditoriaVentaService.filtrarPorFechas(inicio, fin);
        if (auditorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/venta/{idVenta}")
    @Operation(summary = "listar auditorias por venta", description = "devuelve todas las auditorias asociadas a una venta en especifico.")
    public ResponseEntity<List<AuditoriaVenta>> listarPorVenta(@PathVariable Integer idVenta) {
        List<AuditoriaVenta> auditorias = auditoriaVentaService.listarPorVenta(idVenta);
        if (auditorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(auditorias);
    }

}
