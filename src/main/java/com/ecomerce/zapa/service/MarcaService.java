package com.ecomerce.zapa.service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Marca> obtenerPorId(Integer id) {
        return marcaRepository.findById(id);
    }

    public Marca guardarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca actualizarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public void eliminarMarca(Integer id) {
        marcaRepository.deleteById(id);
    }

    public Marca partialUpdate(Marca marca) {
    Marca existingMarca = marcaRepository.findById(marca.getId_marca()).orElse(null);
    if (existingMarca != null) {
        if (marca.getNombre() != null) {
            existingMarca.setNombre(marca.getNombre());
        }
        return marcaRepository.save(existingMarca);
    }
    return null;
}


}
