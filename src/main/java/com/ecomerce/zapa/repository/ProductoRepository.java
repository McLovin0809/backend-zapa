package com.ecomerce.zapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecomerce.zapa.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

    
} 