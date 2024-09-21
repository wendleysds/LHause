package com.lhause.wend.LHouseWeb.controller;

import com.lhause.wend.LHouseWeb.data.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
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
        
        return "index";
    }
    
    @GetMapping("/home")
    public String home(HttpServletRequest request){
        
        var user = (UserEntity)request.getSession().getAttribute("current-user");
        
        if(user.getTipo().equals("cliente"))
            return "clienteHome";
        
        return "funcHome";
    }
}
