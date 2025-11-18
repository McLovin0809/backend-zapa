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

        if (usuario.getNombre() != null)
            existingUsuario.setNombre(usuario.getNombre());
        if (usuario.getEmail() != null)
            existingUsuario.setEmail(usuario.getEmail());
        if (usuario.getClave() != null) {
            existingUsuario.setClave(passwordEncoder.encode(usuario.getClave()));
        }
        if (usuario.getTelefono() != null)
            existingUsuario.setTelefono(usuario.getTelefono());
        if (usuario.getRol() != null)
            existingUsuario.setRol(usuario.getRol());

        return usuarioRepository.save(existingUsuario);
    }

    public Usuario actualizarParcial(Integer id, Usuario datos) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("usuario no encontrado"));

        if (datos.getNombre() != null && !datos.getNombre().isBlank())
            existente.setNombre(datos.getNombre());

        if (datos.getEmail() != null && !datos.getEmail().isBlank())
            existente.setEmail(datos.getEmail());

        if (datos.getClave() != null && !datos.getClave().isBlank())
            existente.setClave(passwordEncoder.encode(datos.getClave()));

        if (datos.getTelefono() != null)
            existente.setTelefono(datos.getTelefono());

        if (datos.getRol() != null)
            existente.setRol(datos.getRol());

        if (datos.getDireccion() != null)
            existente.setDireccion(datos.getDireccion());

        return usuarioRepository.save(existente);
    }

    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    // personalizados
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public List<Usuario> buscarPorRol(String nombreRol) {
        return usuarioRepository.findByRol_Nombre(nombreRol);
    }

}
