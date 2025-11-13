package com.ecomerce.zapa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.Color;
import com.ecomerce.zapa.repository.ColorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;

    public List<Color> listarColores() {
        return colorRepository.findAll();
    }

    public Color obtenerPorId(Integer id) {
        return colorRepository.findById(id).orElse(null);
    }

    public Color registarColor(Color color) {
        return colorRepository.save(color);
    }

    public Color actualizarColor(Integer id, Color color) {
        // color existente
        Color existingColor = colorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("color no encontrado"));

        if (color.getNombre() != null)
            existingColor.setNombre(color.getNombre());

        return colorRepository.save(existingColor);
    }

    public void eliminarColor(Integer id) {
        colorRepository.deleteById(id);
    }

    // personalizados
    public Color buscarPorNombre(String nombre) {
        return colorRepository.findByNombre(nombre);
    }

    public boolean existeColor(String nombre) {
        return colorRepository.existsByNombre(nombre);
    }

}
