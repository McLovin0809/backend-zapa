package com.ecomerce.zapa.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecomerce.zapa.model.Direccion;
import com.ecomerce.zapa.repository.DireccionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    public List<Direccion> listar() {
        return direccionRepository.findAll();
    }

    public Direccion obtener(Integer id) {
        return direccionRepository.findById(id).orElse(null);
    }

    public Direccion registrar(Direccion d) {
        return direccionRepository.save(d);
    }

    public Direccion actualizar(Integer id, Direccion nueva) {
        Direccion existente = obtener(id);
        if (existente == null) return null;

        existente.setCalle(nueva.getCalle());
        existente.setNumero(nueva.getNumero());
        existente.setComuna(nueva.getComuna());

        return direccionRepository.save(existente);
    }

    public Direccion actualizarParcial(Integer id, Direccion cambios) {
        Direccion existente = obtener(id);
        if (existente == null) return null;

        if (cambios.getCalle() != null) existente.setCalle(cambios.getCalle());
        if (cambios.getNumero() != null) existente.setNumero(cambios.getNumero());
        if (cambios.getComuna() != null) existente.setComuna(cambios.getComuna());

        return direccionRepository.save(existente);
    }

    public void eliminar(Integer id) {
        direccionRepository.deleteById(id);
    }
}


