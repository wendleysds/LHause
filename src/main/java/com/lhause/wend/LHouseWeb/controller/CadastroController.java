package com.lhause.wend.LHouseWeb.controller;

import static com.lhause.wend.LHouseWeb.Utils.CheckUser.*;
import com.lhause.wend.LHouseWeb.data.ComputadorEntity;
import com.lhause.wend.LHouseWeb.data.ProdutoEntity;
import com.lhause.wend.LHouseWeb.data.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Wendley S
 */
@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    @GetMapping("/usuario")
    public String cadastrarUsuario(HttpServletRequest request, Model model) {
        if (userIsLogged(request)) {
            return "forbidden";
        }
        
        return "cadastrarUsuario";
    }

    @GetMapping("/produto")
    public String cadastrarProduto(HttpServletRequest request, Model model) {
        if (userRoleIsCliente(request)) {
            return "forbidden";
        }
        
        model.addAttribute("produto", new ProdutoEntity());
        
        return "cadastrarProduto";
    }

    @GetMapping("/computador")
    public String cadastrarComputador(HttpServletRequest request, Model model) {
        if (userRoleIsCliente(request)) {
            return "forbidden";
        }

        model.addAttribute("computador", new ComputadorEntity());
              
        return "cadastrarComputador";
    }
}
