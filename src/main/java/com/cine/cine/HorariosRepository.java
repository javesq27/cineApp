package com.cine.cine;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HorariosRepository extends JpaRepository<Horario, Integer> {

    List<Horario> findByPelicula_IdAndFechaOrderByHora(int idPelicula, Date fecha);

    @Query("select h from Horario h where h.fecha = :fecha and pelicula.estatus='Activa' group by h.pelicula order by pelicula.id asc")
	public List<Horario> findByFecha(Date fecha);
    
}
