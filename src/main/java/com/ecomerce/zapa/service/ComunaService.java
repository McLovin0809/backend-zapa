package com.ecomerce.zapa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.Comuna;
import com.ecomerce.zapa.model.Direccion;
import com.ecomerce.zapa.model.Usuario;
import com.ecomerce.zapa.model.Venta;

import com.ecomerce.zapa.repository.ComunaRepository;
import com.ecomerce.zapa.repository.DireccionRepository;
import com.ecomerce.zapa.repository.UsuarioRepository;
import com.ecomerce.zapa.repository.VentaRepository;

import jakarta.transaction.Transactional;

import com.ecomerce.zapa.repository.ProductosVentaRepository;

@Service
@Transactional
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductosVentaRepository productosVentaRepository;


    // LISTAR
    public List<Comuna> listarTodos() {
        return comunaRepository.findAll();
    }

    // BUSCAR ID
    public Comuna listarPorId(Integer id) {
        return comunaRepository.findById(id).orElse(null);
    }

    // REGISTRAR
    public Comuna registrar(Comuna comuna) {
        return comunaRepository.save(comuna);
    }

    // PUT
    public Comuna actualizar(Integer id, Comuna datos) {
        Comuna existente = comunaRepository.findById(id).orElse(null);
        if (existente == null) return null;

        existente.setNombre(datos.getNombre());
        existente.setRegion(datos.getRegion());
        return comunaRepository.save(existente);
    }

    // PATCH
    public Comuna actualizarParcial(Integer id, Comuna cambios) {
        Comuna existente = comunaRepository.findById(id).orElse(null);
        if (existente == null) return null;

        if (cambios.getNombre() != null)
            existente.setNombre(cambios.getNombre());

        if (cambios.getRegion() != null)
            existente.setRegion(cambios.getRegion());

        return comunaRepository.save(existente);
    }


    // DELETE — cascada manual completa
    public void eliminarComuna(Integer idComuna) {

        // 1. direcciones dentro de la comuna
        List<Direccion> direcciones = direccionRepository.findByComuna_IdComuna(idComuna);

        for (Direccion d : direcciones) {

            // usuarios que viven en esa dirección
            List<Usuario> usuarios = usuarioRepository.findByDireccion_IdDireccion(d.getIdDireccion());

            for (Usuario u : usuarios) {

                // ventas del usuario
                List<Venta> ventas = ventaRepository.findByUsuario_IdUsuario(u.getIdUsuario());

                for (Venta v : ventas) {
                    // eliminar productosVenta de cada venta
                    productosVentaRepository.deleteByVenta_IdVenta(v.getIdVenta());
                }

                // eliminar ventas del usuario
                ventaRepository.deleteByUsuario_IdUsuario(u.getIdUsuario());

                // eliminar usuario
                usuarioRepository.deleteById(u.getIdUsuario());
            }

            // eliminar dirección
            direccionRepository.deleteById(d.getIdDireccion());
        }

        // 3. eliminar comuna
        comunaRepository.deleteById(idComuna);
    }
}
