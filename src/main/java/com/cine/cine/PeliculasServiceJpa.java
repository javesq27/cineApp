package com.cine.cine;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PeliculasServiceJpa implements IPeliculasService {

    @Autowired
    public PeliculasRepository peliculasRepo;

    @Autowired	
	private HorariosRepository horariosRepo;

    @Override
    public void insertar(Pelicula pelicula) {
        peliculasRepo.save(pelicula);
        
    }

    @Override
    public List<Pelicula> buscarTodas() {
        
        return peliculasRepo.findAll();
    }

    @Override
    public Pelicula buscarPorId(int idPelicula) {
        Optional<Pelicula> optional = peliculasRepo.findById(idPelicula);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public List<String> buscarGeneros() {
        List<String> generos = new LinkedList<>();
        generos.add("Accion");
        generos.add("Aventura");
        generos.add("Clasicas");
        generos.add("Comedia Romantica");
        generos.add("Drama");
        generos.add("Terror");
        generos.add("Infantil");
        generos.add("Accion y Aventura");
        generos.add("Romantica");
        generos.add("Ciencia Ficcion");

        return generos;
    }

    @Override
    public void eliminar(int idpelicula) {
        peliculasRepo.deleteById(idpelicula);
        
    }

    @Override
    public List<Pelicula> buscarActivas() {
         
        List<Pelicula> peliculas = peliculasRepo.findByEstatus_OrderByTitulo("Activa");
		return peliculas;
    }

    @Override
    public List<Pelicula> buscarPorFecha(Date fecha) {
        List<Pelicula> peliculas = new LinkedList<>();
        // Buscamos en la tabla de horarios, [agrupando por idPelicula]
		List<Horario> horarios = horariosRepo.findByFecha(fecha);

        // Formamos la lista final de Peliculas que regresaremos.
		for (Horario h : horarios) {
			// Solo nos interesa de cada registro de horario, el registro de pelicula.
			peliculas.add(h.getPelicula());
		}		
		return peliculas;
    }
    
}