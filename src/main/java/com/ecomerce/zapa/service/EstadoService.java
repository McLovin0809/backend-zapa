package com.ecomerce.zapa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.Estado;
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

    // LISTAR
    public List<Estado> listarEstados() {
        return estadoRepository.findAll();
    }

    // BUSCAR POR ID
    public Estado obtenerPorId(Integer id) {
        return estadoRepository.findById(id).orElse(null);
    }

    // REGISTRAR
    public Estado registrarEstado(Estado estado) {
        return estadoRepository.save(estado);
    }

    // PUT - actualizar
    public Estado actualizarEstado(Integer id, Estado nuevoEstado) {
        Estado existente = estadoRepository.findById(id).orElse(null);
        if (existente == null)
            return null;

        existente.setNombre(nuevoEstado.getNombre());
        return estadoRepository.save(existente);
    }

    // DELETE con CASCADA MANUAL
    public void eliminarEstado(Integer idEstado) {

        // 1. ventas asociadas al estado
        List<Venta> ventas = ventaRepository.findByEstado_IdEstado(idEstado);

        for (Venta v : ventas) {

            // 1.1 eliminar productosVenta de esa venta
            productosVentaRepository.deleteByVenta_IdVenta(v.getIdVenta());

            // 1.2 eliminar venta
            ventaRepository.deleteById(v.getIdVenta());
        }

        // 2. eliminar el estado
        estadoRepository.deleteById(idEstado);
    }
}
