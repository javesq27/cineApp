package com.cine.cine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PeliculasServiceImpl implements IPeliculasService {

    private List<Pelicula> lista = null;

    public PeliculasServiceImpl() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        
        try {
            lista = new LinkedList<>();

            Pelicula pelicula1 = new Pelicula();
            pelicula1.setId(1);
            pelicula1.setTitulo("Avengers Endgame");
            pelicula1.setDuracion(150);
            pelicula1.setClasificacion("B");
            pelicula1.setGenero("Accion");
            pelicula1.setFechaEstreno(formatter.parse("05-07-2018"));
            Detalle detalle1 = new Detalle();
            detalle1.setActores("Robert Downey Jr, Chris Evans, Scarlet Johansson");
            detalle1.setDirector("Peee");
            detalle1.setSinopsis("piuuuu");
            pelicula1.setDetalle(detalle1);
    
            Pelicula pelicula2 = new Pelicula();
            pelicula2.setId(2);
            pelicula2.setTitulo("Titanic");
            pelicula2.setDuracion(180);
            pelicula2.setClasificacion("C");
            pelicula2.setGenero("Romantico");
            pelicula2.setFechaEstreno(formatter.parse("02-10-1999"));
            pelicula2.setImagen("titanic.jpg");
            Detalle detalle2 = new Detalle();
            detalle2.setActores("Leo Di caprio, kate Windslet");
            detalle2.setDirector("Steven Spielbergo");
            detalle2.setSinopsis("Gooo onnnn");
            pelicula2.setDetalle(detalle2);
    
            Pelicula pelicula3 = new Pelicula();
            pelicula3.setId(3);
            pelicula3.setTitulo("Mortal Kombat");
            pelicula3.setDuracion(120);
            pelicula3.setClasificacion("B");
            pelicula3.setGenero("Accion");
            pelicula3.setFechaEstreno(formatter.parse("20-03-2021"));
            pelicula3.setImagen("mortal.jpg");
            Detalle detalle3 = new Detalle();
            detalle3.setActores("japiros y chinos");
            detalle3.setDirector("je ne se pas");
            detalle3.setSinopsis("Moortalll Kombaattt");
            pelicula3.setDetalle(detalle3);

            Pelicula pelicula4 = new Pelicula();
            pelicula4.setId(4);
            pelicula4.setTitulo("Spider-man");
            pelicula4.setDuracion(110);
            pelicula4.setClasificacion("c");
            pelicula4.setGenero("Infantil");
            pelicula4.setFechaEstreno(formatter.parse("04-05-2017"));
            pelicula4.setImagen("spider.png");
            pelicula4.setEstatus("Inactiva");
            Detalle detalle4 = new Detalle();
            detalle4.setActores("Tobey Mcguire");
            detalle4.setDirector("Ni idea");
            detalle4.setSinopsis("Spiiderrr");
            pelicula4.setDetalle(detalle4);
    
            lista.add(pelicula1);
            lista.add(pelicula2);
            lista.add(pelicula3);
            lista.add(pelicula4);
    
            

        } catch (ParseException e ) {
            System.out.println("Error" + e.getMessage());
            
        }
    }
    
    
    @Override
    public List<Pelicula> buscarTodas() {
        
        return lista;
    }


    @Override
    public Pelicula buscarPorId(int idPelicula) {
        for(Pelicula p : lista) {
            if(p.getId() == idPelicula) {
                return p; 
            }
        }
        return null;
    }


    @Override
    public void insertar(Pelicula pelicula) {
        lista.add(pelicula);
        
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
        
        
    }


    @Override
    public List<Pelicula> buscarActivas() {
        
        return null;
    }


    @Override
    public List<Pelicula> buscarPorFecha(Date fecha) {
        
        return null;
    }
    
}
