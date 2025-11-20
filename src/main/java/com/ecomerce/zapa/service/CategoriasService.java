package com.ecomerce.zapa.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.ecomerce.zapa.model.Categorias;
import com.ecomerce.zapa.repository.CategoriasRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    public List<Categorias> listar() {
        return categoriasRepository.findAll();
    }

    public Categorias obtener(Integer id) {
        return categoriasRepository.findById(id).orElse(null);
    }

    public Categorias registrar(Categorias c) {
        return categoriasRepository.save(c);
    }

    public Categorias actualizar(Integer id, Categorias nueva) {
        Categorias existente = obtener(id);
        if (existente == null) return null;

        existente.setCategoria(nueva.getCategoria());
        existente.setProducto(nueva.getProducto());
        return categoriasRepository.save(existente);
    }

    public Categorias actualizarParcial(Integer id, Categorias cambios) {
        Categorias existente = obtener(id);
        if (existente == null) return null;

        if (cambios.getCategoria() != null) existente.setCategoria(cambios.getCategoria());
        if (cambios.getProducto() != null) existente.setProducto(cambios.getProducto());

        return categoriasRepository.save(existente);
    }

    public void eliminar(Integer id) {
        categoriasRepository.deleteById(id);
    }
}


