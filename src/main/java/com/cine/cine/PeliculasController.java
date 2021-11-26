package com.cine.cine;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {

    @Autowired
    private IPeliculasService servicePeliculas;

    @Autowired
    private IDetallesService serviceDetalles;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Pelicula> lista = servicePeliculas.buscarTodas();
        model.addAttribute("peliculas", lista);
        return "peliculas/listPeliculas";
    }

    @GetMapping("/create")
    public String crear(Pelicula pelicula, Model model) {
        model.addAttribute("generos", servicePeliculas.buscarGeneros());
        return "peliculas/formPelicula";
    }

    @PostMapping("/save")
    public String guardar(Pelicula pelicula,  BindingResult result, RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart) {

        if(result.hasErrors()) {
            for(ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrio un error: " + error.getDefaultMessage());
            }
            return "peliculas/formPelicula"; 
        }

        if (!multiPart.isEmpty()) {
            //String ruta = "/empleos/img-vacantes/"; // Linux/MAC
            String ruta = "c:/empleos/img-vacantes/"; // Windows
            String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
            if (nombreImagen != null){ // La imagen si se subio
                // Procesamos la variable nombreImagen
                pelicula.setImagen(nombreImagen); 
            }
        }

        System.out.println("La pelicula es: " + pelicula);
        serviceDetalles.insertar(pelicula.getDetalle());
        servicePeliculas.insertar(pelicula);
        attributes.addFlashAttribute("msg", "Registro guardado");
        return "redirect:/peliculas/index";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idPelicula, RedirectAttributes attributes) {
        
        Pelicula pelicula = servicePeliculas.buscarPorId(idPelicula);
        servicePeliculas.eliminar(idPelicula);
        serviceDetalles.eliminar(pelicula.getDetalle().getId());

        attributes.addFlashAttribute("msg", "La pelicula ha sido eliminada");
        return "redirect:/peliculas/index";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int idPelicula, Model model) {
        model.addAttribute("generos", servicePeliculas.buscarGeneros());
        Pelicula pelicula = servicePeliculas.buscarPorId(idPelicula);
        model.addAttribute("pelicula", pelicula);
        
        return "peliculas/formPelicula";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false)); 
    }
    
}
