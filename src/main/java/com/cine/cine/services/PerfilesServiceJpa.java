package com.cine.cine.services;

import com.cine.cine.entities.Perfil;
import com.cine.cine.repositories.PerfilesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilesServiceJpa implements IPerfilesService {

    @Autowired
    private PerfilesRepository perfilRepo;

    @Override
    public void guardar(Perfil perfil) {
        perfilRepo.save(perfil);
        
        
    }
    
}
