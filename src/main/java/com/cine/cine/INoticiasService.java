package com.cine.cine;

import java.util.List;

public interface INoticiasService {
    void guardar(Noticia noticia);
    Noticia buscarPorId(Integer idNoticia);
    void eliminar (Integer idNoticia);
    List<Noticia> buscarTodas();
    List<Noticia> buscarUltimas();
}
