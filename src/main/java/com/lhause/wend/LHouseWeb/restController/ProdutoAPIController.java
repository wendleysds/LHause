package com.lhause.wend.LHouseWeb.restController;

import static com.lhause.wend.LHouseWeb.Utils.CheckUser.*;
import com.lhause.wend.LHouseWeb.data.ProdutoEntity;
import com.lhause.wend.LHouseWeb.service.ProdutoService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Wendley S
 */
@RestController
@RequestMapping("/api/produto")
public class ProdutoAPIController{
    
    @Autowired
    public ProdutoService produtoService;
    
    @GetMapping("")
    public ResponseEntity<List<ProdutoEntity>> getAllProdutos(){
        return ResponseEntity.ok(produtoService.findAllProdutos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoEntity> getProdutoById(@PathVariable Integer id){
        return ResponseEntity.ok(produtoService.findProdutoById(id));
    }
    
    @PostMapping("")
    public ResponseEntity createProduto(HttpServletRequest request, @RequestBody ProdutoEntity produto){
        if(userIsNotLogged(request) || userRoleIsCliente(request))
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        
        if(produto == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        
        produtoService.createProduto(produto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoEntity> updateProduto(HttpServletRequest request, @RequestBody ProdutoEntity produtoRes, @PathVariable Integer id){
        if(userIsNotLogged(request) || userRoleIsCliente(request))
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        
        if(produtoService.findProdutoById(id) == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(produtoService.updateProduto(id, produtoRes));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduto(HttpServletRequest request, @PathVariable Integer id){
        if(userIsNotLogged(request) || userRoleIsCliente(request))
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        
        var produto = produtoService.findProdutoById(id);
        if(produto == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        
        produtoService.deleteProduto(produto.getId());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
