package com.cine.cine;

import java.util.List;

public interface IBannerService {
    void insertar(Banner banner);
    List<Banner> buscarTodos();
    List<Banner> buscarActivos();
    void eliminar(int idBanner);
    Banner buscarporId(int idBanner);
}
