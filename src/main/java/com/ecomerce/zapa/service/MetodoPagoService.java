package com.ecomerce.zapa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.MetodoPago;
import com.ecomerce.zapa.model.Venta;
import com.ecomerce.zapa.repository.MetodoPagoRepository;
import com.ecomerce.zapa.repository.VentaRepository;

import jakarta.transaction.Transactional;

import com.ecomerce.zapa.repository.ProductosVentaRepository;

@Service
@Transactional
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductosVentaRepository productosVentaRepository;

    // LISTAR 
    public List<MetodoPago> listarTodos() {
        return metodoPagoRepository.findAll();
    }

    // OBTENER
    public MetodoPago listarPorId(Integer id) {
        return metodoPagoRepository.findById(id).orElse(null);
    }

    // REGISTRAR
    public MetodoPago registrar(MetodoPago mp) {
        return metodoPagoRepository.save(mp);
    }

    // PUT
    public MetodoPago actualizar(Integer id, MetodoPago nuevo) {
        MetodoPago existente = metodoPagoRepository.findById(id).orElse(null);
        if (existente == null)
            return null;

        existente.setDescripcion(nuevo.getDescripcion());
        return metodoPagoRepository.save(existente);
    }

    // PATCH
    public MetodoPago actualizarParcial(Integer id, MetodoPago cambios) {
        MetodoPago existente = metodoPagoRepository.findById(id).orElse(null);
        if (existente == null)
            return null;

        if (cambios.getDescripcion() != null)
            existente.setDescripcion(cambios.getDescripcion());

        return metodoPagoRepository.save(existente);
    }

    // DELETE con cascada manual
    public void eliminarMetodoPago(Integer idMetodoPago) {

        // ventas con ese método de pago
        List<Venta> ventas = ventaRepository.findByMetodoPago_IdMetodoPago(idMetodoPago);

        for (Venta v : ventas) {
            // eliminar productos vendidos de la venta
            productosVentaRepository.deleteByVenta_IdVenta(v.getIdVenta());

            // eliminar la venta
            ventaRepository.deleteById(v.getIdVenta());
        }

        // eliminar método de pago
        metodoPagoRepository.deleteById(idMetodoPago);
    }
}
