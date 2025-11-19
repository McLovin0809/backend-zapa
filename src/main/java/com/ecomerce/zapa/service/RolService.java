package com.ecomerce.zapa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.Rol;
import com.ecomerce.zapa.model.Usuario;
import com.ecomerce.zapa.repository.RolRepository;
import com.ecomerce.zapa.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
// @SuppressWarnings("null")
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;


    public List<Rol> listarTodos() {
        return rolRepository.findAll();
    }

    public Rol listraPorId(Integer id) {
        return rolRepository.findById(id).orElse(null);
    }

    public Rol registrar(Rol rol) {
        return rolRepository.save(rol);
    }

    public Rol actualizar(Integer id, Rol rolNuevo) {
        Rol existente = rolRepository.findById(id).orElse(null);
        if (existente == null) {
            return null;
        }

        existente.setNombre(rolNuevo.getNombre());
        return rolRepository.save(existente);
    }

    public Rol actualizarParcial(Integer id, Rol cambios) {
        Rol existente = rolRepository.findById(id).orElse(null);
        if (existente == null)
            return null;

        if (cambios.getNombre() != null)
            existente.setNombre(cambios.getNombre());

        return rolRepository.save(existente);
    }

    public void eliminar(Integer id) {
        List<Usuario> usuarios = usuarioRepository.findByRol_IdRol(id);

        for (Usuario u : usuarios) {
            usuarioService.eliminarUsuario(u.getIdUsuario());
        }

        rolRepository.deleteById(id);
    }
    
}
