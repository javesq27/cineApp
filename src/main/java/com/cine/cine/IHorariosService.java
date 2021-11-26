package com.cine.cine;

import java.util.Date;
import java.util.List;

public interface IHorariosService {
    List<Horario> buscarPorIdPelicula(int id, Date fecha);
    void guardar(Horario horario);
    void eliminar(int idHorario);
    Horario buscarPorId(int idHorario);
    List<Horario> buscarTodas();

    
}
