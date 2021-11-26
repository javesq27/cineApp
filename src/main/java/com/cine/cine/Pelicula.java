package com.cine.cine;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Peliculas")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private int duracion;
    private String clasificacion;
    private String genero;
    private String imagen = "cinema.jpg";
    private Date fechaEstreno;
    private String estatus = "Activa";

    @OneToOne
    @JoinColumn(name = "idDetalle")
    private Detalle detalle;

    @OneToMany(mappedBy = "pelicula", fetch = FetchType.EAGER)
    private List<Horario> horarios;

   

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracion() {
        return this.duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getClasificacion() {
        return this.clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFechaEstreno() {
        return this.fechaEstreno;
    }

    public void setFechaEstreno(Date fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public String getEstatus() {
        return this.estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Detalle getDetalle() {
        return this.detalle;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }

    public List<Horario> getHorarios() {
        return this.horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }


    public String toString() {
        return "Pelicula [id=" + id + ", titulo= " + titulo + ", duracion= " + duracion + ", clasificacion= "
                + clasificacion + ", genero= " + genero + ", imagen= " + imagen + ", fechaEstreno=  " + fechaEstreno
                + ", estatus= " + estatus + ", detalle= " + detalle + "]";
    }
    
}
