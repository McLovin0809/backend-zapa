package com.ecomerce.zapa.service;

import com.ecomerce.zapa.repository.ComunaRepository;
import com.ecomerce.zapa.repository.DireccionRepository;
import com.ecomerce.zapa.repository.UsuarioRepository;
import com.ecomerce.zapa.repository.VentaRepository;

@Service
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

    public void eliminarComuna(Integer idComuna) {

        // direcciones dentro de la comuna
        List<Direccion> direcciones = direccionRepository.findByComuna_IdComuna(idComuna);

        for (Direccion d : direcciones) {

            // usuarios que viven en esa dirección
            List<Usuario> usuarios = usuarioRepository.findByDireccion_IdDireccion(d.getIdDireccion());

            for (Usuario u : usuarios) {

                // ventas del usuario
                List<Venta> ventas = ventaRepository.findByUsuario_IdUsuario(u.getIdUsuario());
                for (Venta v : ventas) {
                    productosVentaRepository.deleteByVenta_IdVenta(v.getIdVenta());
                }

                ventaRepository.deleteByUsuario_IdUsuario(u.getIdUsuario());

                usuarioRepository.deleteById(u.getIdUsuario());
            }

            direccionRepository.deleteById(d.getIdDireccion());
        }

        comunaRepository.deleteById(idComuna);
    }

}
