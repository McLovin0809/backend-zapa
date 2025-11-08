package com.ecomerce.zapa.service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Color> obtenerPorId(Integer id) {
        return colorRepository.findById(id);
    }

    public Color guardarColor(Color color) {
        return colorRepository.save(color);
    }

    public Color actualizarColor(Color color) {
        return colorRepository.save(color);
    }

    public void eliminarColor(Integer id) {
        colorRepository.deleteById(id);
    }

    public Color partialUpdate(Color color) {
        Color existingColor = colorRepository.findById(color.getId_color()).orElse(null);
        if (existingColor != null) {
            if (color.getDescripcion() != null) {
                existingColor.setDescripcion(color.getDescripcion());
            }
            return colorRepository.save(existingColor);
        }
        return null;
    }


}
