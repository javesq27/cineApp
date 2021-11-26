package com.cine.cine;


import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BannerServiceImpl implements IBannerService {

    private List<Banner> lista = null;

    public BannerServiceImpl() {
        
        lista = new LinkedList<>();

        
            lista = new LinkedList<>();

            Banner banner1 = new Banner();
            banner1.setId(1);
            banner1.setTitulo("Kong");
            banner1.setArchivo("slide1.jpg");
            

            Banner banner2 = new Banner();
            banner2.setId(2);
            banner2.setTitulo("La bella y la bestia");
            banner2.setArchivo("slide2.jpg");
            

            Banner banner3 = new Banner();
            banner3.setId(3);
            banner3.setTitulo("Fast and furious 8");
            banner3.setArchivo("slide3.jpg");
            

            Banner banner4 = new Banner();
            banner4.setId(4);
            banner4.setTitulo("jefe en pañales");
            banner4.setArchivo("slide4.jpg");
        

            lista.add(banner1);
            lista.add(banner2);
            lista.add(banner3);
            lista.add(banner4);

        } 
            
        
    

    @Override
    public void insertar(Banner banner) {
        lista.add(banner);
        
    }

    @Override
    public List<Banner> buscarTodos() {
        return lista;
    }




    @Override
    public void eliminar(int idBanner) {
        
        
    }




    @Override
    public Banner buscarporId(int idBanner) {
        
        return null;
    }




    @Override
    public List<Banner> buscarActivos() {
        
        return null;
    }
    
}
