package com.lhause.wend.LHouseWeb.controller;

import com.lhause.wend.LHouseWeb.data.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Wendley S
 */
@Controller
public class SiteController {
    
    @GetMapping("/")
    public String index(HttpServletRequest request){
        var session = request.getSession();       
        if(session.getAttribute("current-user") != null){
            return "redirect:/home";
        }
        
        return "redirect:/login";
    }
    
    @GetMapping("/login")
    public String login(HttpServletRequest request){
        var session = request.getSession();       
        if(session.getAttribute("current-user") != null){
            return "redirect:/home";
        }
        
        return "index";
    }
    
    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model){
        
        var user = (UserEntity)request.getSession().getAttribute("current-user");
        
        if(user == null)
            return "redirect:/";
        
        model.addAttribute("nome", user.getNome());
        
        if(user.getTipo().equals("cliente"))
            return "userHome";
        
        return "funcHome";
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        var session = request.getSession();
        session.setAttribute("current-user",null);
        return "redirect:/login";
    }
}
