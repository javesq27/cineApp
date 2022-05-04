package com.cine.cine.services;

import java.util.List;

import com.cine.cine.entities.Banner;

public interface IBannerService {
    void insertar(Banner banner);
    List<Banner> buscarTodos();
    List<Banner> buscarActivos();
    void eliminar(int idBanner);
    Banner buscarporId(int idBanner);
}
