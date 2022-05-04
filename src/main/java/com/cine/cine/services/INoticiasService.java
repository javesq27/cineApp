package com.cine.cine.services;

import java.util.List;

import com.cine.cine.entities.Noticia;

public interface INoticiasService {
    void guardar(Noticia noticia);
    Noticia buscarPorId(Integer idNoticia);
    void eliminar (Integer idNoticia);
    List<Noticia> buscarTodas();
    List<Noticia> buscarUltimas();
}
