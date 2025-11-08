package com.ecomerce.zapa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
/*import org.springframework.web.bind.annotation.DeleteMapping;*/
import org.springframework.web.bind.annotation.GetMapping;
/*import org.springframework.web.bind.annotation.PatchMapping;*/
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.zapa.model.AuditoriaProducto;
import com.ecomerce.zapa.service.AuditoriaProductoService;

@RestController
@RequestMapping("/api/facciones")
public class AuditoriaProducoController {

    @Autowired
    private AuditoriaProductoService auditoriaProductoService;

    @GetMapping
    public ResponseEntity<List<AuditoriaProducto>> getAllauditoriaProducto() {
        List<AuditoriaProducto> auditoriaProductos = auditoriaProductoService.listarAuditorias();//revisar por si no muestra 
        if (auditoriaProductos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(auditoriaProductos);
    }

    @PostMapping
    public ResponseEntity<AuditoriaProducto> createAuditoriaProduto(@RequestBody AuditoriaProducto auditoriaProducto) {
        AuditoriaProducto createAuditoriaProducto = auditoriaProductoService.guardarAuditoria(auditoriaProducto);
        return ResponseEntity.status(201).body(createAuditoriaProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuditoriaProducto> updateAuditoriaProducto(@PathVariable Integer id, @RequestBody AuditoriaProducto auditoriaProducto) {
        auditoriaProducto.setId_auditoria(id);
        AuditoriaProducto updateAuditoriaProducto = auditoriaProductoService.guardarAuditoria(auditoriaProducto);
        if (updateAuditoriaProducto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateAuditoriaProducto);
    }

    /*@PatchMapping("/{id}")
    public ResponseEntity<AuditoriaProducto> updateParcialAuditoriaProducto(@PathVariable long id, @RequestBody AuditoriaProducto auditoriaProducto) {
        auditoriaProducto.setId_auditoria(id);
        AuditoriaProducto updateAuditoriaProducto = auditoriaProductoService.partialUpdate(auditoriaProducto);
        if (updateAuditoriaProducto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateAuditoriaProducto)
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaccion(@PathVariable long id) {
        auditoriaProductoService.dele
    }
    */
}
