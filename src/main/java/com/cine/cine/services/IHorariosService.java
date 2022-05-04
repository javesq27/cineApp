package com.cine.cine.services;

import java.util.Date;
import java.util.List;

import com.cine.cine.entities.Horario;

public interface IHorariosService {
    List<Horario> buscarPorIdPelicula(int id, Date fecha);
    void guardar(Horario horario);
    void eliminar(int idHorario);
    Horario buscarPorId(int idHorario);
    List<Horario> buscarTodas();

    
}
