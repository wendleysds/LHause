package com.lhause.wend.LHouseWeb.controller;

import static com.lhause.wend.LHouseWeb.Utils.CheckUser.*;
import com.lhause.wend.LHouseWeb.service.AgendaService;
import com.lhause.wend.LHouseWeb.service.ComputadorService;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Wendley S
 */
@Controller
@RequestMapping("/agenda")
public class AgendaController {
    
    @Autowired
    private AgendaService agendaService;
    
    @Autowired
    private ComputadorService computadorService;
    
    @GetMapping
    public String index(HttpServletRequest request, Model model){
        if(userIsNotLogged(request))
            return "redirect:/login";
        
        var user = getUser(request);
        
        model.addAttribute("agendas", agendaService.findAllByAgendaByUserId(user.getId()));
        return "agendas";
    }
    
    @GetMapping("/cadastrar")
    public String selecionarComputador(Model model){
        model.addAttribute("computadores", computadorService.findAllComputadores());
        return "selecionarComputador";
    }
    
    @GetMapping("/cadastrar/{id}")
    public String agendar(@PathVariable("id") Integer id, Model model){
        model.addAttribute("computadorId", id);
        model.addAttribute("today", LocalDate.now());
        return "cadastrarAgenda";
    }
}
