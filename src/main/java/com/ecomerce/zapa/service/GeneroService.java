package com.ecomerce.zapa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.Genero;
import com.ecomerce.zapa.repository.GeneroRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    // listar todos
    public List<Genero> listarGeneros() {
        return generoRepository.findAll();
    }

    // buscar por id
    public Genero obtenerPorId(Integer id) {
        return generoRepository.findById(id).orElse(null);
    }

    // registrar
    public Genero registrarGenero(Genero genero) {
        return generoRepository.save(genero);
    }

    // actualizar
    public Genero actualizarGenero(Integer id, Genero generoNuevo) {
        Genero existente = generoRepository.findById(id).orElse(null);
        if (existente == null) {
            return null;
        }

        existente.setNombre(generoNuevo.getNombre());
        return generoRepository.save(existente);
    }

    // eliminar
    public void eliminarGenero(Integer id) {
        generoRepository.deleteById(id);
    }

    // personalizados
    public Genero buscarPorNombreExacto(String nombre) {
        return generoRepository.findByNombre(nombre);
    }

    public boolean existeGenero(String nombre) {
        return generoRepository.existsByNombre(nombre);
    }
}
