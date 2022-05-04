package com.cine.cine.repositories;

import java.util.Date;
import java.util.List;

import com.cine.cine.entities.Noticia;

import org.springframework.data.jpa.repository.JpaRepository;


public interface NoticiasRepository extends JpaRepository<Noticia, Integer> {

    // select * from Noticias
	List<Noticia> findBy();

	// select * from Noticias where estatus = ?
	List<Noticia> findByEstatus(String estatus);

	// where fecha = ?
	List<Noticia> findByFecha(Date fecha);

	// where estatus=? and fecha=?
	List<Noticia> findByEstatusAndFecha(String estatus, Date fecha);

	// where estatus=? or fecha=?
	List<Noticia> findByEstatusOrFecha(String estatus, Date fecha);

	// where fecha between ? and ?
	List<Noticia> findByFechaBetween(Date fecha1, Date fecha2);

	// where id between ? and ?
	List<Noticia> findByIdBetween(int n1, int n2);

	// select * from Noticias where estatus = ? order by id desc limit 3
	List<Noticia> findTop3ByEstatusOrderByIdDesc(String estatus);

}
    

