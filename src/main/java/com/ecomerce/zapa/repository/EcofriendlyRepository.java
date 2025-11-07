package com.ecomerce.zapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.Ecofriendly;

@Repository
public interface EcofriendlyRepository extends JpaRepository<Ecofriendly, Integer> {

    
} 