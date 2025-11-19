package com.ecomerce.zapa.service;

@service
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
