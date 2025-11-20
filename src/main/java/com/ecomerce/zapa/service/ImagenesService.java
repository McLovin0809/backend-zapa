package com.ecomerce.zapa.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.ecomerce.zapa.model.Imagenes;
import com.ecomerce.zapa.repository.ImagenRepository;
import com.ecomerce.zapa.repository.ImagenesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ImagenesService {

    @Autowired
    private ImagenesRepository imagenesRepository;
    @Autowired
    private ImagenRepository imagenRepository;

    public List<Imagenes> listar() {
        return imagenesRepository.findAll();
    }

    public Imagenes obtener(Integer id) {
        return imagenesRepository.findById(id).orElse(null);
    }

    public Imagenes registrar(Imagenes i) {
        return imagenesRepository.save(i);
    }

    public Imagenes actualizar(Integer id, Imagenes nueva) {
        Imagenes existente = obtener(id);
        if (existente == null) return null;

        existente.setProducto(nueva.getProducto());
        existente.setImagen(nueva.getImagen());

        return imagenesRepository.save(existente);
    }

    public Imagenes actualizarParcial(Integer id, Imagenes cambios) {
        Imagenes existente = obtener(id);
        if (existente == null) return null;

        if (cambios.getProducto() != null) existente.setProducto(cambios.getProducto());
        if (cambios.getImagen() != null) existente.setImagen(cambios.getImagen());

        return imagenesRepository.save(existente);
    }

    public void eliminarImagen(Integer idImagen) {

    // 1. eliminar registros intermedios (imagen usada en productos)
    imagenesRepository.deleteByImagen_IdImagen(idImagen);

    // 2. eliminar la imagen
    imagenRepository.deleteById(idImagen);
}


}


