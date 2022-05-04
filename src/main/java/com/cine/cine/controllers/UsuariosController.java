package com.cine.cine.controllers;

import java.util.List;

import com.cine.cine.entities.Perfil;
import com.cine.cine.entities.Usuario;
import com.cine.cine.services.IPerfilesService;
import com.cine.cine.services.IUsuariosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private IUsuariosService serviceUsuarios;

    @Autowired
    private IPerfilesService servicePerfiles;

    @GetMapping("/create")
    public String crear(Usuario usuario) {
        return "usuarios/formUsuario";
    }

    @GetMapping("/index")
    public String index(Model model) {
        List<Usuario> listaUsuarios = serviceUsuarios.buscarTodos();
		model.addAttribute("usuarios", listaUsuarios);
        return "usuarios/listUsuario";
    }

    @PostMapping("/save")
    public String guardar(Usuario usuario, @RequestParam("perfil") String perfil, RedirectAttributes attributes) {

        String tmpPass = usuario.getPwd();
        String encriptado = encoder.encode(tmpPass);
        usuario.setPwd(encriptado);
        usuario.setActivo(1);
        serviceUsuarios.guardar(usuario);

        Perfil perfilTmp = new Perfil();
        perfilTmp.setCuenta(usuario.getCuenta());
        perfilTmp.setPerfil(perfil);
        servicePerfiles.guardar(perfilTmp);

        attributes.addFlashAttribute("msg", "El usuario ha sido guardado!");
        return "redirect:/usuarios/index";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {

        try {
            serviceUsuarios.eliminar(idUsuario);
            attributes.addFlashAttribute("msg", "La usuario  ha sido eliminado");   

        }catch(Exception ex) {
            attributes.addFlashAttribute("msg", "No es posible eliminar el usuario");
        }
        
        return "redirect:/usuarios/index";
    }
    
}