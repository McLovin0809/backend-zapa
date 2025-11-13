package com.ecomerce.zapa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.Venta;
import com.ecomerce.zapa.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    public Venta obtenerPorId(Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }

    public Venta registrarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public Venta actualizarVenta(Integer id, Venta venta) {
        // venta existente
        Venta existingVenta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("venta no encontrada"));

        if (venta.getFechaVenta() != null)
            existingVenta.setFechaVenta(venta.getFechaVenta());
        if (venta.getTotal() != null)
            existingVenta.setTotal(venta.getTotal());

        // Relaciones: solo se actualizan si se envían desde el frontend
        if (venta.getUsuario() != null)
            existingVenta.setUsuario(venta.getUsuario());
        if (venta.getEstado() != null)
            existingVenta.setEstado(venta.getEstado());
        if (venta.getMetodoPago() != null)
            existingVenta.setMetodoPago(venta.getMetodoPago());
        if (venta.getProductosVenta() != null)
            existingVenta.setProductosVenta(venta.getProductosVenta());

        return ventaRepository.save(existingVenta);
    }

    public void eliminarVenta(Integer id) {
        ventaRepository.deleteById(id);
    }
    
    public Venta partialUpdate(Venta venta) {
        Venta existente = ventaRepository.findById(venta.getIdVenta()).orElse(null);
        if (existente != null) {
            if (venta.getUsuario() != null) {
                existente.setUsuario(venta.getUsuario());
            }
            if (venta.getEstado() != null) {
                existente.setEstado(venta.getEstado());
            }
            if (venta.getTotal() != null) {
                existente.setTotal(venta.getTotal());
            }
            return ventaRepository.save(existente);
        }
        return null;
    }

    // personalizados 
    public List<Venta> buscarPorUsuario(Integer idUsuario) {
        return ventaRepository.findByUsuario_IdUsuario(idUsuario);
    }

    public List<Venta> buscarPorEstado(String estado) {
        return ventaRepository.findByEstado_Nombre(estado);
    }

    public List<Venta> buscarPorRangoDeFechas(LocalDateTime desde, LocalDateTime hasta) {
        return ventaRepository.findByFechaVentaBetween(desde, hasta);
    }

}
