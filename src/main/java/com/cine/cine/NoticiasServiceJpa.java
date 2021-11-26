package com.cine.cine;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiasServiceJpa implements INoticiasService {

    @Autowired
    private NoticiasRepository noticiasRepo;

    @Override
    public void guardar(Noticia noticia) {
        noticiasRepo.save(noticia);
        
    }

    @Override
    public Noticia buscarPorId(Integer idNoticia) {
        Optional<Noticia> optional =  noticiasRepo.findById(idNoticia);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;

    }

    @Override
    public void eliminar(Integer idNoticia) {
        noticiasRepo.deleteById(idNoticia);
        
    }

    @Override
    public List<Noticia> buscarTodas() {
        
        return noticiasRepo.findAll();
    }

    @Override
    public List<Noticia> buscarUltimas() {
        List<Noticia> noticias = noticiasRepo.findTop3ByEstatusOrderByIdDesc("Activa");		
		return noticias;
    }
    
}
