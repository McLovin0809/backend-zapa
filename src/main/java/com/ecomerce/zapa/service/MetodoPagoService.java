package com.ecomerce.zapa.service;

@Service
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductosVentaRepository productosVentaRepository;

    public void eliminarMetodoPago(Integer idMetodoPago) {

        List<Venta> ventas = ventaRepository.findByMetodoPago_IdMetodoPago(idMetodoPago);

        for (Venta v : ventas) {
            productosVentaRepository.deleteByVenta_IdVenta(v.getIdVenta());
            ventaRepository.deleteById(v.getIdVenta());
        }

        metodoPagoRepository.deleteById(idMetodoPago);
    }
}

