package com.lhause.wend.LHouseWeb.restController;

import com.lhause.wend.LHouseWeb.model.CarrinhoProduto;
import com.lhause.wend.LHouseWeb.service.ProdutoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Wendley S
 */
@RestController
@RequestMapping("/carrinho") 
public class CarrinhoController {

    @Autowired
    private ProdutoService produtoService;
    
    private final String CARRINHO_ATTRIBUTE_NAME = "carrinho";
    private final String CARRINHO_COOKIE_NAME = "carrinho-cookie";
    
    @GetMapping("")
    public ResponseEntity<List<CarrinhoProduto>> getProdutos(HttpServletRequest request) {
        return ResponseEntity.ok(getCarrinho(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarrinhoProduto> getProduto(HttpServletRequest request, @PathVariable("id") Integer id) {
        var carrinho = getCarrinho(request);
        
        for(var p : carrinho){
            if(p.getProduto().getId().equals(id))
                return ResponseEntity.ok(p);
        }
        
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("")
    public ResponseEntity clearCarrinho(HttpServletRequest request){
        var session = request.getSession();
        session.setAttribute(CARRINHO_ATTRIBUTE_NAME, new ArrayList<CarrinhoProduto>());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
    @PostMapping("")
    public ResponseEntity setCarrinho(HttpServletRequest request, @RequestBody ArrayList<CarrinhoProduto> carrinhoProdutos){
        int i = 0;
        for(var c : carrinhoProdutos){
            var p = produtoService.findProdutoById(c.getProduto().getId());          
            if(p == null){
                carrinhoProdutos.remove(i);
                i++;
                continue;
            }            
            c.setQuantidade(clamp(c.getQuantidade(), 1, p.getEstoque()));   
            
            i++;
        }    
        request.getSession().setAttribute(CARRINHO_ATTRIBUTE_NAME, carrinhoProdutos);
        
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @PostMapping("/savecookie")
    public ResponseEntity setCarrinhoCookie(@RequestBody String carrinhoJson, ArrayList<CarrinhoProduto> carrinho, HttpServletResponse response) {    
        Cookie carrinhoCookie = new Cookie(CARRINHO_COOKIE_NAME, carrinhoJson);
        carrinhoCookie.setDomain("localhost");
        carrinhoCookie.setHttpOnly(true);
        carrinhoCookie.setMaxAge(604800); // 7 days
        response.addCookie(carrinhoCookie);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    private List<CarrinhoProduto> getCarrinhoAt(HttpServletRequest request){
        var session = request.getSession();
        var carrinho = new ArrayList<CarrinhoProduto>();
        
        if(session.getAttribute(CARRINHO_ATTRIBUTE_NAME) == null){
            session.setAttribute(CARRINHO_ATTRIBUTE_NAME, carrinho);
            return carrinho;
        }
        
        carrinho = (ArrayList<CarrinhoProduto>) session.getAttribute(CARRINHO_ATTRIBUTE_NAME);
        return carrinho;
    }
    
    private List<CarrinhoProduto> getCarrinhoCo(HttpServletRequest request){
        var session = request.getSession();
        var carrinho = new ArrayList<CarrinhoProduto>();
        
        if(session.getAttribute(CARRINHO_ATTRIBUTE_NAME) == null){
            var cookies = request.getCookies();
            if (cookies.length > 0) {
                for (var cookie : request.getCookies()) {
                    if (cookie.getName().equals(CARRINHO_COOKIE_NAME)) {
                        var parser = new JSONParser(cookie.getValue());
                        try {
                            carrinho = (ArrayList<CarrinhoProduto>) parser.parse();
                            session.setAttribute(CARRINHO_ATTRIBUTE_NAME, carrinho);
                            return carrinho;
                        } catch (Exception e) {
                            session.setAttribute(CARRINHO_ATTRIBUTE_NAME, carrinho);
                            return carrinho;
                        }
                    }
                }
            }
        }
        
        return null;
    }
    
    private List<CarrinhoProduto> getCarrinho(HttpServletRequest request) {      
        List<CarrinhoProduto> carrinho = null;
        
        carrinho = getCarrinhoCo(request);
        
        if(carrinho == null)
            return getCarrinhoAt(request);
        
        return carrinho;
    }
    
    private Integer clamp(Integer value, Integer min, Integer max){
        if(value > max)
            return max;
        
        if(value < min)
            return min;
        
        return value;
    }
}
