package com.cine.cine;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByCuenta(String cuenta);
}
