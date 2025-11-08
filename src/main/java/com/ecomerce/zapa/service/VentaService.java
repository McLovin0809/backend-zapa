package com.ecomerce.zapa.service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Venta> obtenerPorId(Integer id) {
        return ventaRepository.findById(id);
    }

    public Venta registrarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public Venta actualizarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public void eliminarVenta(Integer id) {
        ventaRepository.deleteById(id);
    }
    
    public Venta partialUpdate(Venta venta) {
        Venta existente = ventaRepository.findById(venta.getId_venta()).orElse(null);
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

}
