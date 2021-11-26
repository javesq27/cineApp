package com.cine.cine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculasRepository extends JpaRepository<Pelicula, Integer> {

    // Listado de peliculas filtradas por estatus
	public List<Pelicula> findByEstatus_OrderByTitulo(String estatus);
    
}
