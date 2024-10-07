package com.lhause.wend.LHouseWeb.controller;

import static com.lhause.wend.LHouseWeb.Utils.CheckUser.*;
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
    
    @GetMapping("")
    public String index(HttpServletRequest request){
        if(userIsNotLogged(request))
            return "redirect:/login";
        
        return "loja";
    }
    
    @GetMapping("/compras")
    public String compras(HttpServletRequest request){
        if(userIsNotLogged(request))
            return "redirect:/login";
        
        return "clienteCompras";
    }
    
    @GetMapping("/pesquisa/venda")
    public String pesquisarVenda(HttpServletRequest request){
        if(userIsNotLogged(request))
            return "redirect:/login";
        
        if(userRoleIsCliente(request))
            return "forbidden";
        
        return "historicoCompras";
    }
    
    @GetMapping("/carrinho")
    public String carrinho(HttpServletRequest request){
        if(userIsNotLogged(request))
            return "redirect:/login";
        
        return "carrinho";
    }
    
    @GetMapping("/finalizar")
    public String finalizarCompra(HttpServletRequest request){
        if(userIsNotLogged(request))
            return "redirect:/login";
        
        return "finalizarCompra";
    }
}
