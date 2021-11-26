package com.cine.cine;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
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
