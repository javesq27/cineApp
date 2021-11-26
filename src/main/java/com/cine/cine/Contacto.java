package com.cine.cine;

import java.util.Arrays;
import java.util.List;

public class Contacto {

    private int id;
    private String nombre;
    private String email;
    private int rating;
    private String[] generos;
    private List<String> notificaciones;
    private String comentarios;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String[] getGeneros() {
        return this.generos;
    }

    public void setGeneros(String[] generos) {
        this.generos = generos;
    }

    public List<String> getNotificaciones() {
        return this.notificaciones;
    }

    public void setNotificaciones(List<String> notificaciones) {
        this.notificaciones = notificaciones;
    }

    public String getComentarios() {
        return this.comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String toString() {
        return "Contacto [id=" + id + ", nombre=" + nombre + ", email=" +  email + ", rating=" + rating + 
        ", generos=" + Arrays.toString(generos) + ", notificaciones=" + notificaciones +
        ", comentarios" + "]";  
    }
    
}
