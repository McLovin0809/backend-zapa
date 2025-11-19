package com.ecomerce.zapa.service;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductosVentaRepository productosVentaRepository;

    public void eliminarEstado(Integer idEstado) {

        List<Venta> ventas = ventaRepository.findByEstado_IdEstado(idEstado);

        for (Venta v : ventas) {
            productosVentaRepository.deleteByVenta_IdVenta(v.getIdVenta());
            ventaRepository.deleteById(v.getIdVenta());
        }

        estadoRepository.deleteById(idEstado);
    }
}

