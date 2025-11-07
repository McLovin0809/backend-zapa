package com.ecomerce.zapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecomerce.zapa.model.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer>{

    
} 