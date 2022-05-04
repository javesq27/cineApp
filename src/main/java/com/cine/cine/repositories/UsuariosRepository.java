package com.cine.cine.repositories;

import com.cine.cine.entities.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByCuenta(String cuenta);
}
