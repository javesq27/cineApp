package com.cine.cine;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller    
public class HomeController {

    @Autowired
    private IPeliculasService servicePeliculas;

    @Autowired
    private IBannerService serviceBanners;

    @Autowired
    private IHorariosService serviceHorarios;

    @Autowired
	private INoticiasService serviceNoticias;

    @Autowired
    private IUsuariosService serviceUsuarios;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @PostMapping("/search")
    public String buscar(@RequestParam("fecha") String fecha, Model model) {
        
        List<String> listaFechas = Utileria.getNextDays(4);
        List<Pelicula> peliculas = servicePeliculas.buscarActivas();

        model.addAttribute("fechas", listaFechas);
        model.addAttribute("fechaBusqueda", fecha);
        model.addAttribute("peliculas", peliculas); 

        List<Banner> banners = serviceBanners.buscarTodos();
        model.addAttribute("banners", banners);

        return "home";
        
       
    }

    @GetMapping("/")
    public String mostrarPrincipal(Model model) {
        
        List<String> listaFechas = Utileria.getNextDays(4);
        List<Pelicula> peliculas = servicePeliculas.buscarActivas();

        model.addAttribute("fechas", listaFechas);
        model.addAttribute("fechaBusqueda", dateFormat.format(new Date()));
        model.addAttribute("peliculas", peliculas); 

        List<Banner> banners = serviceBanners.buscarTodos();
        model.addAttribute("banners", banners);

        return "home";
        
    }

    @GetMapping("/index")
    public String mostrarIndex(Authentication auth, HttpSession session) {
        String cuenta = auth.getName();
        System.out.println("Nombre del usuario: " + cuenta);

       for(GrantedAuthority rol : auth.getAuthorities()) {
           System.out.println("ROL: " + rol.getAuthority());
       }
       
       if(session.getAttribute("usuario") == null) {
           Usuario usuario = serviceUsuarios.buscarPorCuenta(cuenta);
           usuario.setPwd(null);
           System.out.println("Usuario: " + usuario);
           session.setAttribute("usuario", usuario);

       }
       
       
        return "redirect:/";
    }
    
    
    
    //@GetMapping("/detail/{id}/{fecha}")
    @GetMapping("/detail")
    //public String mostrarDetalle(@PathVariable(name = "id") String idPelicula, @PathVariable(name = "fecha") String fecha, Model model) {
        public String mostrarDetalle(@RequestParam(name = "idMovie") int idPelicula, @RequestParam(name = "fecha") Date fecha, Model model) {  /*
        String tituloPelicula = "Rapidos y furiosos";
        int duracion = 130;
        double precioEntrada = 50;
        
        model.addAttribute("pelicula", tituloPelicula);
        model.addAttribute("duracion", duracion);
        model.addAttribute("precio", precioEntrada);
        */
        List<Horario> horarios = serviceHorarios.buscarPorIdPelicula(idPelicula, fecha);
        model.addAttribute("horarios", horarios);
        model.addAttribute("fechaBusqueda", dateFormat.format(fecha));
        model.addAttribute("pelicula", servicePeliculas.buscarPorId(idPelicula));
        System.out.println("para la fecha: " + fecha);
        return "detalle";
        
    }

    @GetMapping("/about")
	public String mostrarAcerca() {			
		return "acerca";
	}

    @GetMapping("/login")
    public String mostrarLogin() {
        return "formLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null); 
        return "redirect:/";
}
	
	@ModelAttribute("noticias")
	public List<Noticia> getNoticias(){
		return serviceNoticias.buscarUltimas();
	}
	
	@ModelAttribute("banners")
	public List<Banner> getBanners(){
		return serviceBanners.buscarActivos();
	}

    @GetMapping("/bcrypt/{texto}")
    @ResponseBody
    public String encriptar(@PathVariable("texto") String texto) {
        return texto + " Encriptado en Bcrypt: " + passwordEncoder.encode(texto);

    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false)); 
    }

   



        
}
    
