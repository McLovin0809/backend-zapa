package com.ecomerce.zapa.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.Usuario;
import com.ecomerce.zapa.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario registrarUsuario(Usuario usuario) {
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Integer id, Usuario usuario) {
        Usuario existingUsuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("usuario no encontrado"));
        
        if(usuario.getNombre() != null) existingUsuario.setNombre(usuario.getNombre());
        if(usuario.getEmail()!= null) existingUsuario.setEmail(usuario.getEmail());
        if(usuario.getClave() != null) {
            existingUsuario.setClave(passwordEncoder.encode(usuario.getClave()));
        }
        if(usuario.getTelefono() != null) existingUsuario.setTelefono(usuario.getTelefono());
        if (usuario.getRol() != null)existingUsuario.setRol(usuario.getRol());

        return usuarioRepository.save(existingUsuario);
    }

    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public boolean validarCredenciales(String email, String passwordPlano) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            return passwordEncoder.matches(passwordPlano, usuario.getClave());
        }
        return false;
    }

}
