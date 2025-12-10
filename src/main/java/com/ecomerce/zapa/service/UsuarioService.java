package com.ecomerce.zapa.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ecomerce.zapa.model.*;
import com.ecomerce.zapa.repository.*;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private RolRepository rolRepository;
    @Autowired private ComunaRepository comunaRepository;
    @Autowired private DireccionRepository direccionRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario registrarUsuario(Usuario usuario) {
        Integer idRol = usuario.getRol() != null ? usuario.getRol().getIdRol() : null;
        if (idRol == null || !rolRepository.existsById(idRol)) {
            throw new RuntimeException("Rol no válido");
        }
        Rol rol = rolRepository.findById(idRol).orElseThrow();

        Integer idComuna = usuario.getDireccion() != null &&
                           usuario.getDireccion().getComuna() != null
                           ? usuario.getDireccion().getComuna().getIdComuna() : null;

        if (idComuna == null || !comunaRepository.existsById(idComuna)) {
            throw new RuntimeException("Comuna no válida");
        }
        Comuna comuna = comunaRepository.findById(idComuna).orElseThrow();

        Direccion direccion = usuario.getDireccion();
        direccion.setComuna(comuna);
        direccion = direccionRepository.save(direccion);

        usuario.setRol(rol);
        usuario.setDireccion(direccion);
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));

        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Integer id, Usuario usuario) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("usuario no encontrado"));

        if (usuario.getNombre() != null) existente.setNombre(usuario.getNombre());
        if (usuario.getEmail() != null)  existente.setEmail(usuario.getEmail());
        if (usuario.getClave() != null)  existente.setClave(passwordEncoder.encode(usuario.getClave()));
        if (usuario.getTelefono() != null) existente.setTelefono(usuario.getTelefono());
        if (usuario.getRol() != null) existente.setRol(usuario.getRol());
        if (usuario.getDireccion() != null) existente.setDireccion(usuario.getDireccion());

        return usuarioRepository.save(existente);
    }

    public Usuario login(Usuario usuario) {
        Usuario found = usuarioRepository.findByEmail(usuario.getEmail());
        if (found != null && passwordEncoder.matches(usuario.getClave(), found.getClave())) {
            return found;
        }
        return null;
    }

    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public List<Usuario> buscarPorRol(String nombreRol) {
        return usuarioRepository.findByRol_Nombre(nombreRol);
    }
}
