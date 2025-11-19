package com.ecomerce.zapa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.Venta;
import com.ecomerce.zapa.repository.ProductosVentaRepository;
import com.ecomerce.zapa.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductosVentaRepository productosVentaRepository;


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

    public Venta actualizarParcial(Integer id, Venta datos) {
        Venta existente = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("venta no encontrada"));

        if (datos.getFechaVenta() != null)
            existente.setFechaVenta(datos.getFechaVenta());

        if (datos.getTotal() != null)
            existente.setTotal(datos.getTotal());

        if (datos.getUsuario() != null)
            existente.setUsuario(datos.getUsuario());

        if (datos.getEstado() != null)
            existente.setEstado(datos.getEstado());

        if (datos.getMetodoPago() != null)
            existente.setMetodoPago(datos.getMetodoPago());

        // productosVenta NO se actualiza aquí (se actualiza con su propio controller)

        return ventaRepository.save(existente);
    }

    public void eliminarVenta(Integer id) {
        // 1. eliminar detalle venta
        productosVentaRepository.deleteByVenta_IdVenta(id);

        // 2. eliminar venta
        ventaRepository.deleteById(id);
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
