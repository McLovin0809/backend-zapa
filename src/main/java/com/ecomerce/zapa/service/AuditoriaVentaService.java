package com.ecomerce.zapa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.AuditoriaVenta;
import com.ecomerce.zapa.model.Venta;
import com.ecomerce.zapa.repository.AuditoriaVentaRepository;
import com.ecomerce.zapa.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuditoriaVentaService {
    @Autowired
    private AuditoriaVentaRepository auditoriaVentaRepository;

    @Autowired
    private VentaRepository ventaRepository;

    public List<AuditoriaVenta> listarAuditoriasVentas() {
        return auditoriaVentaRepository.findAll();
    }

    public AuditoriaVenta registrarAuditoriaVenta(AuditoriaVenta auditoriaVenta) {
        auditoriaVenta.setFechaRegistro(LocalDateTime.now());
        return auditoriaVentaRepository.save(auditoriaVenta);
    }

     // registra auto una nueva v 
    public AuditoriaVenta registrarNuevaVersion(Integer idVenta, Double totalVenta, Double ganancia) {
        Venta venta = ventaRepository.findById(idVenta)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        AuditoriaVenta nuevaAuditoria = new AuditoriaVenta();
        nuevaAuditoria.setVenta(venta);
        nuevaAuditoria.setTotalVenta(totalVenta);
        nuevaAuditoria.setGanancia(ganancia);
        nuevaAuditoria.setFechaRegistro(LocalDateTime.now());

        return auditoriaVentaRepository.save(nuevaAuditoria);
    }

    public AuditoriaVenta obtenerPorId(Integer id) {
        return auditoriaVentaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("auditoria de venta no encontrada"));
    }

    // metodos persolazidaos
    public List<AuditoriaVenta> filtrarPorFechas(LocalDateTime inicio, LocalDateTime fin) {
        return auditoriaVentaRepository.findByFechaRegistroBetween(inicio, fin);
    }

    public List<AuditoriaVenta> listarPorVenta(Integer idVenta) {
        return auditoriaVentaRepository.findByVenta_IdVenta(idVenta);
    }

}
