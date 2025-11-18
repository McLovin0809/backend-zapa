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

import com.ecomerce.zapa.model.AuditoriaProducto;
import com.ecomerce.zapa.model.Usuario;
import com.ecomerce.zapa.service.AuditoriaProductoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/facciones")
public class AuditoriaProducoController {

    @Autowired
    private AuditoriaProductoService auditoriaProductoService;

    @GetMapping
    @Operation(summary = "obtener todas las auditorias productos", description = "DEVUELVE la lista de auditorias de productos registradas.")
    public ResponseEntity<List<AuditoriaProducto>> listarAuditoriasProductos() {
        List<AuditoriaProducto> auditorias = auditoriaProductoService.listarAuditoriasproductos();
        if (auditorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/producto/{idProducto}")
    @Operation(summary = "buscar auditoria producto por id", description = "devuelve una auditoria producto segn su id")
    public ResponseEntity<List<AuditoriaProducto>> buscarPorProducto(@PathVariable Integer idProducto) {
        List<AuditoriaProducto> auditorias = auditoriaProductoService.buscarPorProducto(idProducto);
        if (auditorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(auditorias);
    }

    @PostMapping
    @Operation(summary = "agregar una auditoria producto", description = "crea una auditoria producto nueva")
    public ResponseEntity<AuditoriaProducto> registrarAuditoriaManual(@RequestBody AuditoriaProducto auditoria) {
        AuditoriaProducto nueva = auditoriaProductoService.registrarAuditoriaProductos(auditoria);
        return ResponseEntity.status(201).body(nueva);
    }

    // add atomaticamente una nueva versión cuando se actualiza un producto
    @PostMapping("/registrar/{idProducto}")
    @Operation(summary = "registrar nueva version de auditoria", description = "crea automaticamente una nueva auditoria cuando un producto es actualizado, guardando el precio anterior, el nuevo y el descuento aplicado.")
    public ResponseEntity<AuditoriaProducto> registrarNuevaVersion(
            @PathVariable Integer idProducto,
            @RequestParam Integer idUsuario,
            @RequestParam Double precioAnterior,
            @RequestParam Double precioNuevo,
            @RequestParam(required = false, defaultValue = "0") Double descuento) {

        // asignamos
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);

        AuditoriaProducto nueva = auditoriaProductoService.registrarNuevaVersion(
                idProducto, usuario, precioAnterior, precioNuevo, descuento);

        return ResponseEntity.status(201).body(nueva);
    }

    // personalizados
    @GetMapping("/admin/{nombreAdmin}")
    @Operation(summary = "historial por administrador", description = "muestra todas las auditorías realizadas por un administrador.")
    public ResponseEntity<List<AuditoriaProducto>> buscarPorAdministrador(@PathVariable String nombreAdmin) {
        List<AuditoriaProducto> auditorias = auditoriaProductoService.buscarPorAdministrador(nombreAdmin);
        if (auditorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/fechas")
    @Operation(summary = "filtrar por rango de fechas", description = "devuelve auditorías entre dos fechas especificadas.")
    public ResponseEntity<List<AuditoriaProducto>> buscarPorFechas(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        List<AuditoriaProducto> auditorias = auditoriaProductoService.buscarPorRangoDeFechas(inicio, fin);
        if (auditorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(auditorias);
    }

    /*
     * @PatchMapping("/{id}")
     * public ResponseEntity<AuditoriaProducto>
     * updateParcialAuditoriaProducto(@PathVariable long id, @RequestBody
     * AuditoriaProducto auditoriaProducto) {
     * auditoriaProducto.setId_auditoria(id);
     * AuditoriaProducto updateAuditoriaProducto =
     * auditoriaProductoService.partialUpdate(auditoriaProducto);
     * if (updateAuditoriaProducto == null) {
     * return ResponseEntity.notFound().build();
     * }
     * return ResponseEntity.ok(updateAuditoriaProducto)
     * }
     * 
     * 
     * @DeleteMapping("/{id}")
     * public ResponseEntity<Void> deleteFaccion(@PathVariable long id) {
     * auditoriaProductoService.dele
     * }
     */

}
