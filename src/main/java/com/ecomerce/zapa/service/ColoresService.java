package com.ecomerce.zapa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.Colores;
import com.ecomerce.zapa.repository.ColoresRepository;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class ColoresService {

    @Autowired
    private ColoresRepository coloresRepository;

    public List<Colores> listar() {
        return coloresRepository.findAll();
    }

    public Colores obtener(Integer id) {
        return coloresRepository.findById(id).orElse(null);
    }

    public Colores registrar(Colores c) {
        return coloresRepository.save(c);
    }

    public Colores actualizar(Integer id, Colores nuevo) {
        Colores existente = obtener(id);
        if (existente == null) return null;

        existente.setProducto(nuevo.getProducto());
        existente.setColor(nuevo.getColor());
        return coloresRepository.save(existente);
    }

    public Colores actualizarParcial(Integer id, Colores cambios) {
        Colores existente = obtener(id);
        if (existente == null) return null;

        if (cambios.getProducto() != null) existente.setProducto(cambios.getProducto());
        if (cambios.getColor() != null) existente.setColor(cambios.getColor());

        return coloresRepository.save(existente);
    }

    public void eliminar(Integer id) {
        coloresRepository.deleteById(id);
    }
}



