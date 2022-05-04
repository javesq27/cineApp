package com.cine.cine.services;

import java.util.Date;
import java.util.List;

import com.cine.cine.entities.Pelicula;

public interface IPeliculasService {
    void insertar(Pelicula pelicula);
    List<Pelicula> buscarTodas();
    Pelicula buscarPorId(int idPelicula);
    List<String> buscarGeneros();
    void eliminar(int idpelicula);
    List<Pelicula> buscarActivas();	
	List<Pelicula> buscarPorFecha(Date fecha);
}
