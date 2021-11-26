package com.cine.cine;

import java.util.List;

public interface IUsuariosService {
    void guardar(Usuario usuario);
    void eliminar(int idUsuario);
    Usuario buscarPorId(int idUsuario);
    List<Usuario> buscarTodos();
    Usuario buscarPorCuenta(String cuenta);
    
}
