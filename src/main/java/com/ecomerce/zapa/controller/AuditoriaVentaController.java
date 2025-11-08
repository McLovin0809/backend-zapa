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

import com.ecomerce.zapa.model.AuditoriaVenta;
import com.ecomerce.zapa.service.AuditoriaVentaService;

@RestController
@RequestMapping("/api/auditorias-venta")
public class AuditoriaVentaController {

    @Autowired
    private AuditoriaVentaService auditoriaVentaService;

    @GetMapping
    public ResponseEntity<List<AuditoriaVenta>> getAllAuditoriasVenta() {
        List<AuditoriaVenta> auditorias = auditoriaVentaService.listarAuditorias();
        if (auditorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(auditorias);
    }

    @PostMapping
    public ResponseEntity<AuditoriaVenta> createAuditoriaVenta(@RequestBody AuditoriaVenta auditoriaVenta) {
        AuditoriaVenta creada = auditoriaVentaService.guardarAuditoria(auditoriaVenta);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuditoriaVenta> updateAuditoriaVenta(@PathVariable Integer id, @RequestBody AuditoriaVenta auditoriaVenta) {
        auditoriaVenta.setId_auditoria_venta(id);
        AuditoriaVenta actualizada = auditoriaVentaService.guardarAuditoria(auditoriaVenta);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuditoriaVenta> updateParcialAuditoriaVenta(@PathVariable Integer id, @RequestBody AuditoriaVenta auditoriaVenta) {
        auditoriaVenta.setId_auditoria_venta(id);
        AuditoriaVenta actualizada = auditoriaVentaService.parcialudate(auditoriaVenta);
        if (actualizada == null) {
        return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }


}

