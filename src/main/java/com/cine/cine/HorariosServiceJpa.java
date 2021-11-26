package com.cine.cine;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HorariosServiceJpa implements IHorariosService {

    @Autowired
    private HorariosRepository repoHorarios;

    @Override
    public List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha) {
        return repoHorarios.findByPelicula_IdAndFechaOrderByHora(idPelicula, fecha);
        
    }

    @Override
    public void guardar(Horario horario) {
        repoHorarios.save(horario);
        
    }

    @Override
    public void eliminar(int idHorario) {
        repoHorarios.deleteById(idHorario);
        
    }

    @Override
    public Horario buscarPorId(int idHorario) {
        Optional<Horario> optional = repoHorarios.findById(idHorario);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public List<Horario> buscarTodas() {
        
        return repoHorarios.findAll();
    }
    
}
