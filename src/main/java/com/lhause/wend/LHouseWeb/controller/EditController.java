package com.lhause.wend.LHouseWeb.controller;

import static com.lhause.wend.LHouseWeb.Utils.CheckUser.getUser;
import static com.lhause.wend.LHouseWeb.Utils.CheckUser.userRoleIsCliente;
import com.lhause.wend.LHouseWeb.service.ComputadorService;
import com.lhause.wend.LHouseWeb.service.ProdutoService;
import com.lhause.wend.LHouseWeb.service.UserService;
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
@RequestMapping("/editar")
public class EditController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ComputadorService computadorService;
    
    @GetMapping("/usuario")
    public String editarCurrentUsuario(HttpServletRequest request, Model model) {
        if (userRoleIsCliente(request)) {
            return "forbidden";
        }

        model.addAttribute("usuario", getUser(request));
        
        return "cadastrarUsuario";
    }

    @GetMapping("/usuario/{id}")
    public String editarUsuario(HttpServletRequest request, Model model, @PathVariable("id") Integer id) {
        if (userRoleIsCliente(request)) {
            return "forbidden";
        }
        
        var user = userService.findUserById(id);
        if(user == null)
            return "error";
        
        model.addAttribute("usuario", user);
        
        return "cadastrarUsuario";
    }

    @GetMapping("/produto/{id}")
    public String editarProduto(HttpServletRequest request, Model model, @PathVariable("id") Integer id) {
        if (userRoleIsCliente(request)) {
            return "forbidden";
        }
        
        var produto = produtoService.findProdutoById(id);
        if(produto == null)
            return "error";
        
        model.addAttribute("produto", produto);
        
        return "cadastrarProduto";
    }

    @GetMapping("/computador/{id}")
    public String editarComputador(HttpServletRequest request, Model model, @PathVariable("id") Integer id) {
        if (userRoleIsCliente(request)) {
            return "forbidden";
        }

        var computador = computadorService.findComputadorById(id);
        if(computador == null)
            return "error";
        
        model.addAttribute("computador", computador);
        
        return "cadastrarComputador";
    }
}
