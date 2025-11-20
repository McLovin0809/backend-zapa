package com.ecomerce.zapa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.Region;
import com.ecomerce.zapa.model.Comuna;
import com.ecomerce.zapa.repository.RegionRepository;

import jakarta.transaction.Transactional;

import com.ecomerce.zapa.repository.ComunaRepository;

@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private ComunaService comunaService; // para cascada completa

    // LISTAR TODOS
    public List<Region> listarTodos() {
        return regionRepository.findAll();
    }

    // BUSCAR POR ID
    public Region listarPorId(Integer id) {
        return regionRepository.findById(id).orElse(null);
    }

    // REGISTRAR
    public Region registrar(Region region) {
        return regionRepository.save(region);
    }

    // PUT
    public Region actualizar(Integer id, Region datos) {
        Region existente = regionRepository.findById(id).orElse(null);
        if (existente == null)
            return null;

        existente.setNombre(datos.getNombre());
        return regionRepository.save(existente);
    }

    // PATCH
    public Region actualizarParcial(Integer id, Region cambios) {
        Region existente = regionRepository.findById(id).orElse(null);
        if (existente == null)
            return null;

        if (cambios.getNombre() != null) {
            existente.setNombre(cambios.getNombre());
        }

        return regionRepository.save(existente);
    }

    // DELETE CON CASCADA
    public void eliminarRegion(Integer idRegion) {

        // 1. obtener comunas de la región
        List<Comuna> comunas = comunaRepository.findByRegion_IdRegion(idRegion);

        // 2. por cada comuna → cascada completa
        for (Comuna c : comunas) {
            comunaService.eliminarComuna(c.getIdComuna());
        }

        // 3. eliminar la región
        regionRepository.deleteById(idRegion);
    }
}
