package com.ecomerce.zapa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.Talla;
import com.ecomerce.zapa.repository.TallaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TallaService {
    @Autowired
    private TallaRepository tallaRepository;

    public List<Talla> listarTallas() {
        return tallaRepository.findAll();
    }

    public Talla obtenerPorId(Integer id) {
        return tallaRepository.findById(id).orElse(null);
    }

    public Talla registarTalla(Talla talla) {
        return tallaRepository.save(talla);
    }

    public Talla actualizarTalla(Integer id, Talla talla) {
        // Buscar la talla existente
        Talla existingTalla = tallaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("talla no encontrada"));

        // Actualizar solo si el valor no es nulo
        if (talla.getNumero() != null)
            existingTalla.setNumero(talla.getNumero());

        return tallaRepository.save(existingTalla);
    }

    public void eliminarTalla(Integer id) {
        tallaRepository.deleteById(id);
    }

    // peronalizados
    public Talla buscarPorNumero(String numero) {
        return tallaRepository.findByNumero(numero);
    }

    public boolean existePorNumero(String numero) {
        return tallaRepository.existsByNumero(numero);
    }
}
