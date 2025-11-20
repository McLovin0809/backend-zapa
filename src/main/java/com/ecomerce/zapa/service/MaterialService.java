package com.ecomerce.zapa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.Material;
import com.ecomerce.zapa.repository.MaterialRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    // listar todos
    public List<Material> listarMateriales() {
        return materialRepository.findAll();
    }

    // buscar por id
    public Material obtenerPorId(Integer id) {
        return materialRepository.findById(id).orElse(null);
    }

    // registrar
    public Material registrarMaterial(Material material) {
        return materialRepository.save(material);
    }

    // actualizar
    public Material actualizarMaterial(Integer id, Material nuevo) {
        Material existente = materialRepository.findById(id).orElse(null);
        if (existente == null) {
            return null;
        }

        existente.setNombre(nuevo.getNombre());
        return materialRepository.save(existente);
    }

    // eliminar
    public void eliminarMaterial(Integer id) {
        materialRepository.deleteById(id);
    }

    // personalizados
    public Material buscarPorNombreExacto(String nombre) {
        return materialRepository.findByNombre(nombre);
    }

    public boolean existeMaterial(String nombre) {
        return materialRepository.existsByNombre(nombre);
    }
}
