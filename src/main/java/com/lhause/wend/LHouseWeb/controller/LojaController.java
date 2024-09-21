package com.lhause.wend.LHouseWeb.controller;

import com.lhause.wend.LHouseWeb.data.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Wendley S
 */
@Controller
@RequestMapping("/loja")
public class LojaController {
    
    @GetMapping("/")
    public String index(HttpServletRequest request){
        if(!userIsLoged(request))
            return "redirect:/login";
        
        return "loja";
    }
     
    private boolean userRoleIsCliente(HttpServletRequest request){
        var user = (UserEntity)request.getSession().getAttribute("current-user");
        return user.getTipo().equals("cliente");
    }
    
    private boolean userIsLoged(HttpServletRequest request){
        var session = request.getSession();      
        try{
            var user = (UserEntity)session.getAttribute("current-user");
            if(user == null)
                return false;
        }catch(Exception ex){
            return false;
        }
        
        return true;
    }
}
