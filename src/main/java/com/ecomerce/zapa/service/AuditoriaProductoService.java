package com.ecomerce.zapa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.AuditoriaProducto;
import com.ecomerce.zapa.repository.AuditoriaProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuditoriaProductoService {
    @Autowired
    private AuditoriaProductoRepository auditoriaProductoRepository;

    public List<AuditoriaProducto> listarAuditorias() {
        return auditoriaProductoRepository.findAll();
    }

    public List<AuditoriaProducto> buscarPorProducto(Integer idProducto) {
        return auditoriaProductoRepository.findByProducto_IdProducto(idProducto);
    }

    public List<AuditoriaProducto> buscarPorAdministrador(String nombreAdmin) {
        return auditoriaProductoRepository.findByUsuario_Nombre(nombreAdmin);
    }

    public List<AuditoriaProducto> buscarPorRangoDeFechas(LocalDateTime inicio, LocalDateTime fin) {
        return auditoriaProductoRepository.findByFechaCambioBetween(inicio, fin);
    }

    public AuditoriaProducto guardarAuditoria(AuditoriaProducto auditoria) {
        return auditoriaProductoRepository.save(auditoria);
    }

}
