package com.cine.cine.services;

import java.util.List;
import java.util.Optional;

import com.cine.cine.entities.Banner;
import com.cine.cine.repositories.BannersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannersServiceJpa implements IBannerService {

    @Autowired
    private BannersRepository repoBanners;

    @Override
    public void insertar(Banner banner) {
        repoBanners.save(banner);
        
    }

    @Override
    public List<Banner> buscarTodos() {
        
        return repoBanners.findAll();
    }

    @Override
    public void eliminar(int idBanner) {
        repoBanners.deleteById(idBanner);
        
    }

    @Override
    public Banner buscarporId(int idBanner) {
        Optional<Banner> optional = repoBanners.findById(idBanner);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public List<Banner> buscarActivos() {
        
        return repoBanners.findByEstatusOrderByIdDesc("Activo");
    }
    
}
