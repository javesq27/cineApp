package com.cine.cine.controllers;

import java.util.List;

import com.cine.cine.entities.Banner;
import com.cine.cine.services.IBannerService;
import com.cine.cine.utils.Utileria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/banners")
public class BannersController {

    @Autowired
    private IBannerService serviceBanners;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Banner> banners = serviceBanners.buscarTodos();
        model.addAttribute("banners", banners);
        
        return "banners/listBanners";
    }

    @GetMapping("/create")
    public String crear(Banner banner) {
        return "banners/formBanner";
    }

    @PostMapping("/save")
    public String guardar(Banner banner, BindingResult result, RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart) {

        if(result.hasErrors()) {
            for(ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrio un error: " + error.getDefaultMessage());
            }
            return "banners/formBanner"; 
        }

        if (!multiPart.isEmpty()) {
            //String ruta = "/empleos/img-vacantes/"; // Linux/MAC
            String ruta = "c:/empleos/img-vacantes/"; // Windows
            String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
            if (nombreImagen != null){ // La imagen si se subio
                // Procesamos la variable nombreImagen
                banner.setArchivo(nombreImagen);
            }
        }

        serviceBanners.insertar(banner);
        attributes.addFlashAttribute("msg", "Registro guardado");
        return "redirect:/banners/index";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idBanner, RedirectAttributes attributes) {
        
        
        serviceBanners.eliminar(idBanner);
       

        attributes.addFlashAttribute("msg", "El banner ha sido eliminado");
        return "redirect:/banners/index";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int idBanner, Model model) {
        Banner banner = serviceBanners.buscarporId(idBanner);
        model.addAttribute("banner", banner);
        
        return "banners/formBanner";
    }

    
}
