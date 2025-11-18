package com.ecomerce.zapa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Integer>{
    Usuario findByEmail(String email); // login

    List<Usuario> findByRol_Nombre(String rol); // listar clientes o admins

    
} 