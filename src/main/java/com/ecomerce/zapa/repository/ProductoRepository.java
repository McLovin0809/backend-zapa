package com.ecomerce.zapa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

    // uscar  x marca
    List<Producto> findByMarca_Nombre(String nombreMarca);

    // buscar x categoría
    List<Producto> findByCategorias_Categoria_Nombre(String nombreCategoria);

    // buscar x gene
    List<Producto> findByGenero_Nombre(String nombreGenero);

    // buscar productos dentro de un rango de precios
    List<Producto> findByPrecioBetween(Double min, Double max);

    // buscar productos con descuento activo
    List<Producto> findByDescuentoActivo(Double descuento);


} 