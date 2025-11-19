package com.ecomerce.zapa.service;

import java.util.List;

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

    public Ecofriendly obtenerPorId(Integer id) {
        return ecofriendlyRepository.findById(id).orElse(null);
    }

    public Ecofriendly registarEcofriendly(Ecofriendly ecofriendly) {
        return ecofriendlyRepository.save(ecofriendly);
    }

    public Ecofriendly actualizarEcofriendly(Integer id, Ecofriendly ecofriendly) {
        // registro existente
        Ecofriendly existingEco = ecofriendlyRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("ecofriendly no encontrado"));

        existingEco.setEsEcofriendly(ecofriendly.isEsEcofriendly());

        return ecofriendlyRepository.save(existingEco);
    }

    public Ecofriendly actualizarParcial(Integer id, Ecofriendly ecoFriendly) {
        Ecofriendly existente = ecofriendlyRepository.findById(id).orElse(null);
        if (existente == null)
            return null;

        // solo cambia si viene en el body
        existente.setEsEcofriendly(ecoFriendly.isEsEcofriendly());

        return ecofriendlyRepository.save(existente);
    }

    public void eliminarEcofriendly(Integer id) {
        ecofriendlyRepository.deleteById(id);
    }

}
