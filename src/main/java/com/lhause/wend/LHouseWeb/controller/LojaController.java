package com.lhause.wend.LHouseWeb.controller;

import static com.lhause.wend.LHouseWeb.Utils.CheckUser.*;
import com.lhause.wend.LHouseWeb.service.ProdutoService;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/loja")
public class LojaController {
    
    @Autowired
    private ProdutoService produtoService;
    
    @GetMapping("")
    public String index(HttpServletRequest request, Model model){
        if(userIsNotLogged(request))
            return "redirect:/login";
        
        return "loja";
    }
    
    @GetMapping("/pesquisa/venda")
    public String pesquisarVenda(HttpServletRequest request){
        if(userIsNotLogged(request))
            return "redirect:/login";
        
        if(userRoleIsCliente(request))
            return "forbidden";
        
        return "";
    }
    
    @GetMapping("/carrinho")
    public String carrinho(HttpServletRequest request){
        if(userIsNotLogged(request))
            return "redirect:/login";
        
        return "carrinho";
    }
    
    @GetMapping("/carrinho/{produtoId}")
    public String carrinhoProduto(@PathVariable("produtoId") Integer produtoId, Model model, HttpServletRequest request){
        if(userIsNotLogged(request))
            return "redirect:/login";
        
        model.addAttribute("produtoId", produtoId);
        
        return "";
    }
    
    @GetMapping("/finalizar")
    public String finalizarCompra(HttpServletRequest request){
        if(userIsNotLogged(request))
            return "redirect:/login";
        
        return "";
    }
}
