package com.ecomerce.zapa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.Venta;
import com.ecomerce.zapa.repository.EstadoRepository;
import com.ecomerce.zapa.repository.ProductosVentaRepository;
import com.ecomerce.zapa.repository.VentaRepository;

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

