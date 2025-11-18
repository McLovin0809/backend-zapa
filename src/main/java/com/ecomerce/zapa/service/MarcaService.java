package com.ecomerce.zapa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.Marca;
import com.ecomerce.zapa.repository.MarcaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> listarMarcas() {
        return marcaRepository.findAll();
    }

    public Marca obtenerPorId(Integer id) {
        return marcaRepository.findById(id).orElse(null);
    }

    public Marca registarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca actualizarMarca(Integer id, Marca marca) {
        Marca existingMarca = marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("marca no encontrada"));

        if (marca.getNombre() != null)
            existingMarca.setNombre(marca.getNombre());

        return marcaRepository.save(existingMarca);
    }

    public Marca actualizarParcial(Integer id, Marca cambios) {
        Marca existente = marcaRepository.findById(id).orElse(null);
        if (existente == null)
            return null;

        if (cambios.getNombre() != null)
            existente.setNombre(cambios.getNombre());

        return marcaRepository.save(existente);
    }

    public void eliminarMarca(Integer id) {
        marcaRepository.deleteById(id);
    }

    // personalizados
    public Marca buscarPorNombreExacto(String nombre) {
        return marcaRepository.findByNombre(nombre);
    }

    public List<Marca> buscarPorNombreParcial(String nombre) {
        return marcaRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public boolean existeMarca(String nombre) {
        return marcaRepository.existsByNombre(nombre);
    }

}
