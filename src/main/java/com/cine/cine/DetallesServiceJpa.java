package com.cine.cine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetallesServiceJpa implements IDetallesService {

    @Autowired
    private DetallesRepository detallesRepo;

    @Override
    public void insertar(Detalle detalle) {
        detallesRepo.save(detalle);

    }

    @Override
    public void eliminar(int idDetalle) {
        detallesRepo.deleteById(idDetalle);
        
    }
    
}
