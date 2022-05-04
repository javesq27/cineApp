package com.cine.cine.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.cine.cine.entities.Horario;
import com.cine.cine.entities.Pelicula;
import com.cine.cine.services.IHorariosService;
import com.cine.cine.services.IPeliculasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/horarios")
public class HorariosController {

    @Autowired
    private IPeliculasService servicePeliculas;

    @Autowired
    private IHorariosService serviceHorarios;

    @GetMapping(value = "/index")
	public String mostrarIndex(Model model) {
		List<Horario> listaHorarios = serviceHorarios.buscarTodas();
		model.addAttribute("horarios", listaHorarios);
		return "horarios/listHorarios";
	}

    @GetMapping("/create")
    public String crear(Horario horario, Model model) {
        List<Pelicula> peliculas = servicePeliculas.buscarTodas();
        model.addAttribute("peliculas", peliculas);
        return "horarios/formHorario";
        
    }

    @PostMapping("/save")
    public String guardar(Horario horario,  BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()) {
            for(ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrio un error: " + error.getDefaultMessage());
            }
            return "horarios/formHorario";
        }
        serviceHorarios.guardar(horario);
        attributes.addFlashAttribute("msg", "El horario ha sido guardado");
        return "redirect:/horarios/index";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idHorario, RedirectAttributes attributes) {
        
        serviceHorarios.eliminar(idHorario);

        attributes.addFlashAttribute("msg", "El horario ha sido eliminado");
        return "redirect:/horarios/index";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int idHorario, Model model) {
        
        Horario horario = serviceHorarios.buscarPorId(idHorario);
        model.addAttribute("horario", horario);
        
        return "horarios/formHorario";
    }

    @ModelAttribute("peliculas")
	public List<Pelicula> getPeliculas(){
		return servicePeliculas.buscarActivas();
	}

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false)); 
    }
    
}
