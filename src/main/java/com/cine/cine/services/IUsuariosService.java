package com.cine.cine.services;

import java.util.List;

import com.cine.cine.entities.Usuario;

public interface IUsuariosService {
    void guardar(Usuario usuario);
    void eliminar(int idUsuario);
    Usuario buscarPorId(int idUsuario);
    List<Usuario> buscarTodos();
    Usuario buscarPorCuenta(String cuenta);
    
}
