package com.cine.cine.services;

import com.cine.cine.entities.Detalle;

public interface IDetallesService {
    void insertar(Detalle detalle);
    void eliminar(int idDetalle);
    
}
