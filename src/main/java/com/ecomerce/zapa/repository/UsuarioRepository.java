package com.ecomerce.zapa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecomerce.zapa.model.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer>{
    Usuario findByEmail(String email); // login

    List<Usuario> findByRol_Nombre(String rol); // listar clientes o admins

    Usuario findByEmailAndPassword(String email, String password); // login simple (sin seguridad)

    
} 