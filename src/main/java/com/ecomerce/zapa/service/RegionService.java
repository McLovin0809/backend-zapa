package com.ecomerce.zapa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.Comuna;
import com.ecomerce.zapa.repository.ComunaRepository;
import com.ecomerce.zapa.repository.RegionRepository;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private ComunaService comunaService;

    public void eliminarRegion(Integer idRegion) {

        // comunas de esa región
        List<Comuna> comunas = comunaRepository.findByRegion_IdRegion(idRegion);

        for (Comuna c : comunas) {
            comunaService.eliminarComuna(c.getIdComuna());
        }

        regionRepository.deleteById(idRegion);
    }

}
