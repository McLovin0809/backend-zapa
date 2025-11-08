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

    /* 
    public AuditoriaProducto partialUpdate(AuditoriaProducto auditoriaProducto){
        AuditoriaProducto existingAuditoriaProducto = auditoriaProductoRepository.findById(auditoriaProducto.getId_auditoria()).orElse(null);
        if(existingAuditoriaProducto != null) {
            existingAuditoriaProducto.setDescuento(auditoriaProducto.getDescuento());
        }
        if(existingAuditoriaProducto != null) {
            existingAuditoriaProducto.setFechaCambio(auditoriaProducto.getFechaCambio());
        }
        if (existingAuditoriaProducto != null) {
            existingAuditoriaProducto.setId_auditoria(auditoriaProducto.getId_auditoria());
        }
        if (existingAuditoriaProducto != null) {
            existingAuditoriaProducto.setMotivo(auditoriaProducto.getMotivo());
        }
        if (existingAuditoriaProducto != null) {
            existingAuditoriaProducto.setPrecioAnterior(auditoriaProducto.getPrecioAnterior());
        }
        if (existingAuditoriaProducto != null) {
            existingAuditoriaProducto.setPrecioNuevo(auditoriaProducto.getPrecioNuevo());
        }
        if (existingAuditoriaProducto != null) {
            existingAuditoriaProducto.setProducto(auditoriaProducto.getProducto());
        }
    }*/

    /*public void deleteById(long id){
        naverService.deleteByAuditoriaProducto(id);

    }*/

}
