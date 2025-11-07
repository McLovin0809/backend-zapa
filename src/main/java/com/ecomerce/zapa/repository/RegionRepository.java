package com.ecomerce.zapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.zapa.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer>{

    
}