package com.ecomerce.zapa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.zapa.model.Rol;
import com.ecomerce.zapa.repository.RolRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
//@SuppressWarnings("null")
public class RolService {
    
    @Autowired
    private RolRepository rolRepository;

    public List<Rol> findAll(){
        return rolRepository.findAll();
    }

    public Rol findById(Integer id) {
        return rolRepository.findById(id).orElse(null);
    }

    
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    } 
}
