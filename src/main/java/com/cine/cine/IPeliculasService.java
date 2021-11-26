package com.cine.cine;

import java.util.Date;
import java.util.List;

public interface IPeliculasService {
    void insertar(Pelicula pelicula);
    List<Pelicula> buscarTodas();
    Pelicula buscarPorId(int idPelicula);
    List<String> buscarGeneros();
    void eliminar(int idpelicula);
    // Con este metodo traemos las peliculas Activas. Para formar el select de Peliculas del FORM de nuevo Horario.
    List<Pelicula> buscarActivas();	
	List<Pelicula> buscarPorFecha(Date fecha);
}
