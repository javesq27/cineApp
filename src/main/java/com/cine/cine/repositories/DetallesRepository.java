package com.cine.cine.repositories;

import com.cine.cine.entities.Detalle;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallesRepository extends JpaRepository<Detalle, Integer> {
    
}
