package com.ecomerce.zapa.service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Talla> obtenerPorId(Integer id) {
        return tallaRepository.findById(id);
    }

    public Talla guardarTalla(Talla talla) {
        return tallaRepository.save(talla);
    }

    public Talla actualizarTalla(Talla talla) {
        return tallaRepository.save(talla);
    }

    public void eliminarTalla(Integer id) {
        tallaRepository.deleteById(id);
    }

}
