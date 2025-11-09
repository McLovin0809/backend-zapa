package com.ecomerce.zapa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.AuditoriaProducto;
import com.ecomerce.zapa.model.Producto;
import com.ecomerce.zapa.model.Usuario;
import com.ecomerce.zapa.repository.AuditoriaProductoRepository;
import com.ecomerce.zapa.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuditoriaProductoService {
    @Autowired
    private AuditoriaProductoRepository auditoriaProductoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<AuditoriaProducto> listarAuditoriasproductos() {
        return auditoriaProductoRepository.findAll();
    }

    public List<AuditoriaProducto> buscarPorProducto(Integer idProducto) {
        return auditoriaProductoRepository.findByProducto_IdProducto(idProducto);
    }

    public List<AuditoriaProducto> buscarPorAdministrador(String nombreAdmin) {
        return auditoriaProductoRepository.findByUsuario_Nombre(nombreAdmin);
    }

    public List<AuditoriaProducto> buscarPorRangoDeFechas(LocalDateTime inicio, LocalDateTime fin) {
        return auditoriaProductoRepository.findByFechaCambioBetween(inicio, fin);
    }

    public AuditoriaProducto registrarAuditoriaProductos(AuditoriaProducto auditoria) {
        return auditoriaProductoRepository.save(auditoria);
    }

    // registrar una nueva versión cuando cambia el producto
    public AuditoriaProducto registrarNuevaVersion(Integer idProducto, Usuario usuario, Double precioAnterior, Double precioNuevo, Double descuento) {
        Producto producto = productoRepository.findById(idProducto)
            .orElseThrow(() -> new RuntimeException("producto no encontrado"));

        AuditoriaProducto nuevaAuditoria = new AuditoriaProducto();
        nuevaAuditoria.setProducto(producto);
        nuevaAuditoria.setFechaCambio(LocalDateTime.now());
        nuevaAuditoria.setPrecioAnterior(precioAnterior);
        nuevaAuditoria.setPrecioNuevo(precioNuevo);
        nuevaAuditoria.setDescuento(descuento);
        nuevaAuditoria.setUsuario(usuario); 

        return auditoriaProductoRepository.save(nuevaAuditoria);
    }

    public void deleteById(Integer id){
        auditoriaProductoRepository.deleteById(id);
    }

}
