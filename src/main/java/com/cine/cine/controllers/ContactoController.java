package com.cine.cine.controllers;

import com.cine.cine.entities.Contacto;
import com.cine.cine.services.IPeliculasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactoController {

    @Autowired
    private IPeliculasService servicePeliculas;

    @GetMapping("/contacto")
    public String mostrarFormulario(Contacto contacto, Model model) {
        model.addAttribute("generos", servicePeliculas.buscarGeneros());
        return "formContacto";
    }

    @PostMapping("/contacto")
    public String guardar(Contacto contacto, RedirectAttributes attributes) {
        System.out.println(contacto);
        attributes.addFlashAttribute("msg", "Gracias por enviarnos tu opinion!.");
        return "redirect:/contacto";
    }
    
}
