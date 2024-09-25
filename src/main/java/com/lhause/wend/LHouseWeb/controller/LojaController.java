package com.lhause.wend.LHouseWeb.controller;

import static com.lhause.wend.LHouseWeb.Utils.CheckUser.*;
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
        if(!userIsLogged(request))
            return "redirect:/login";
        
        return "loja";
    }
}
