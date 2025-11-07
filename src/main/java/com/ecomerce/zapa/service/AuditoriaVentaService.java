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

}
