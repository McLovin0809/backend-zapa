package com.ecomerce.zapa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.Categoria;
import com.ecomerce.zapa.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria obtenerPorId(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public Categoria registarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizarCategoria(Integer id, Categoria categoria) {
        // categoria existente
        Categoria existingCategoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("categoria no encontrada"));

        if (categoria.getNombre() != null)
            existingCategoria.setNombre(categoria.getNombre());

        return categoriaRepository.save(existingCategoria);
    }

    public void eliminarCategoria(Integer id) {
        categoriaRepository.deleteById(id);
    }

    // personalizados
    public Categoria buscarPorNombreExacto(String nombre) {
        return categoriaRepository.findByNombre(nombre);
    }

    public boolean existeCategoria(String nombre) {
        return categoriaRepository.existsByNombre(nombre);
    }
}
