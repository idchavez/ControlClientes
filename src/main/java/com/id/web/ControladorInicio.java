package com.id.web;

import com.id.domain.Persona;
import com.id.servicio.IPersonaService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorInicio {
    
    @Autowired
    private IPersonaService personaService;
    
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        
        var personas = personaService.listarPersonas();
        
        log.info("executing Spring MVC controller");
        
        log.info("Welcome: " + user);
        
        model.addAttribute("personas", personas);
        
        var saldoTotal = 0D;
        
        for(var p : personas){
            saldoTotal += p.getSaldo();
        }
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("totalClientes", personas.size());
        
        return "index";
    }
    
    @GetMapping("/agregar")
    public String agregar(Persona persona){
        return "modificar";
    }
    
    //recibir el objeto del formulario spring lo
    //inyecta automaticamente al definirlo como argumento
    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores){
        if (errores.hasErrors()) {
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }
    
    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
        personaService.eliminar(persona);
        return "redirect:/";
    }
}
