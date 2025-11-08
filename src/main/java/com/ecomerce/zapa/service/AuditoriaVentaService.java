package com.ecomerce.zapa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.AuditoriaVenta;
import com.ecomerce.zapa.repository.AuditoriaVentaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuditoriaVentaService {
    @Autowired
    private AuditoriaVentaRepository auditoriaVentaRepository;

    public List<AuditoriaVenta> listarAuditorias() {
        return auditoriaVentaRepository.findAll();
    }

    public AuditoriaVenta guardarAuditoria(AuditoriaVenta auditoria) {
        return auditoriaVentaRepository.save(auditoria);
    }

    public AuditoriaVenta parcialudate(AuditoriaVenta auditoriaVenta){
        AuditoriaVenta existingAuditoriaVenta = auditoriaVentaRepository.findById(auditoriaVenta.getId_auditoria_venta()).orElse(null);
        if (existingAuditoriaVenta != null) {
            if (auditoriaVenta.getFechaRegistro() != null) {
                existingAuditoriaVenta.setFechaRegistro(auditoriaVenta.getFechaRegistro());
            }
            if (auditoriaVenta.getGanancia() != null) {
                existingAuditoriaVenta.setGanancia(auditoriaVenta.getGanancia());
            }
            if (auditoriaVenta.getTotalVenta() != null) {
                existingAuditoriaVenta.setTotalVenta(existingAuditoriaVenta.getTotalVenta());
            }
            if (auditoriaVenta.getVenta() != null) {
                existingAuditoriaVenta.setVenta(existingAuditoriaVenta.getVenta());
            }
            return auditoriaVentaRepository.save(existingAuditoriaVenta);
        }
        return null;
    }

}
