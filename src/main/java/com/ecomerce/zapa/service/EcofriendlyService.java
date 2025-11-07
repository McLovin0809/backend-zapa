package com.ecomerce.zapa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.Ecofriendly;
import com.ecomerce.zapa.repository.EcofriendlyRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EcofriendlyService {
    @Autowired
    private EcofriendlyRepository ecofriendlyRepository;

    public List<Ecofriendly> listarEcofriendly() {
        return ecofriendlyRepository.findAll();
    }

    public Optional<Ecofriendly> obtenerPorId(Integer id) {
        return ecofriendlyRepository.findById(id);
    }

    public Ecofriendly guardarEcofriendly(Ecofriendly ecofriendly) {
        return ecofriendlyRepository.save(ecofriendly);
    }

    public Ecofriendly actualizarEcofriendly(Ecofriendly ecofriendly) {
        return ecofriendlyRepository.save(ecofriendly);
    }

    public void eliminarEcofriendly(Integer id) {
        ecofriendlyRepository.deleteById(id);
    }

}
