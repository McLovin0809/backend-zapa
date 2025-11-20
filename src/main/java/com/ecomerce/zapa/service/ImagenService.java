package com.ecomerce.zapa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.Imagen;
import com.ecomerce.zapa.repository.ImagenRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    // GET ALL
    public List<Imagen> listar() {
        return imagenRepository.findAll();
    }

    // GET BY ID
    public Imagen obtenerPorId(Integer id) {
        return imagenRepository.findById(id).orElse(null);
    }

    // POST
    public Imagen registrar(Imagen imagen) {
        return imagenRepository.save(imagen);
    }

    // PUT (actualiza todo)
    public Imagen actualizar(Integer id, Imagen nueva) {
        Imagen existente = imagenRepository.findById(id).orElse(null);
        if (existente == null) return null;

        existente.setUrl(nueva.getUrl());
        existente.setDescripcion(nueva.getDescripcion());

        return imagenRepository.save(existente);
    }

    // PATCH (actualiza parcial)
    public Imagen actualizarParcial(Integer id, Imagen cambios) {
        Imagen existente = imagenRepository.findById(id).orElse(null);
        if (existente == null) return null;

        if (cambios.getUrl() != null)
            existente.setUrl(cambios.getUrl());

        if (cambios.getDescripcion() != null)
            existente.setDescripcion(cambios.getDescripcion());

        return imagenRepository.save(existente);
    }

    // DELETE
    public void eliminar(Integer id) {
        imagenRepository.deleteById(id);
    }
}


