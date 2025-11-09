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

@RestController
@RequestMapping("/api/auditorias-venta")
public class AuditoriaVentaController {

    @Autowired
    private AuditoriaVentaService auditoriaVentaService;

    @GetMapping
    public ResponseEntity<List<AuditoriaVenta>> listarAuditoriasVentas() {
        List<AuditoriaVenta> auditorias = auditoriaVentaService.listarAuditoriasVentas();
        if (auditorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditoriaVenta> obtenerAuditoriaPorId(@PathVariable Integer id) {
        try {
            AuditoriaVenta auditoria = auditoriaVentaService.obtenerPorId(id);
            return ResponseEntity.ok(auditoria);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AuditoriaVenta> registrarAuditoriaVenta(@RequestBody AuditoriaVenta auditoriaVenta) {
        AuditoriaVenta nueva = auditoriaVentaService.registrarAuditoriaVenta(auditoriaVenta);
        return ResponseEntity.status(201).body(nueva);
    }

     // agrega de forma auto una nueva versión vinculada a una venta
    @PostMapping("/registrar/{idVenta}")
    public ResponseEntity<AuditoriaVenta> registrarNuevaVersion(
            @PathVariable Integer idVenta,
            @RequestParam Double totalVenta,
            @RequestParam Double ganancia) {

        AuditoriaVenta nueva = auditoriaVentaService.registrarNuevaVersion(idVenta, totalVenta, ganancia);
        return ResponseEntity.status(201).body(nueva);
    }

    // personalizados
    @GetMapping("/filtrar")
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
    public ResponseEntity<List<AuditoriaVenta>> listarPorVenta(@PathVariable Integer idVenta) {
        List<AuditoriaVenta> auditorias = auditoriaVentaService.listarPorVenta(idVenta);
        if (auditorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(auditorias);
    }

}

