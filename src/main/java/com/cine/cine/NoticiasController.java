package com.cine.cine;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/noticias")
public class NoticiasController {

    @Autowired
    private INoticiasService serviceNoticias;

    @GetMapping(value = "/index")
	public String mostrarIndex(Model model) {
		List<Noticia> listaNoticias = serviceNoticias.buscarTodas();
		model.addAttribute("noticias", listaNoticias);
		return "noticias/listNoticias";
    }
    
    @GetMapping("/create")
    public String crear(Noticia noticia) {
        return "noticias/formNoticia";
    }

    @PostMapping("/save")
    public String guardar(Noticia noticia, Model model, RedirectAttributes attributes) {
        
        
        serviceNoticias.guardar(noticia);
        attributes.addFlashAttribute("msg", "Los datos de la noticia fueron guardados!");
		return "redirect:/noticias/index";
    }
    
    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idNoticia, RedirectAttributes attributes) {
        
        
        serviceNoticias.eliminar(idNoticia);
       

        attributes.addFlashAttribute("msg", "La noticia ha sido eliminada");
        return "redirect:/noticias/index";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int idNoticia, Model model) {
        Noticia noticia = serviceNoticias.buscarPorId(idNoticia);
        model.addAttribute("Noticia", noticia);
        
        return "noticias/formNoticia";
    }
}
