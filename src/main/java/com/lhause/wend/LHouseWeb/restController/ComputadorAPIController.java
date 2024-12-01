package com.lhause.wend.LHouseWeb.restController;

import static com.lhause.wend.LHouseWeb.Utils.CheckUser.*;
import com.lhause.wend.LHouseWeb.data.ComputadorEntity;
import com.lhause.wend.LHouseWeb.service.ComputadorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/computador")
public class ComputadorAPIController {
    
    @Autowired
    private ComputadorService computadorService;
    
    @GetMapping("")
    public ResponseEntity getAllComputadores(){
        return ResponseEntity.ok(computadorService.findAllComputadores());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity getComputadorById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(computadorService.findComputadorById(id));
    }
    
    @PostMapping("")
    public ResponseEntity createComputador(HttpServletRequest request, @RequestBody ComputadorEntity computador){
        if(userRoleIsCliente(request))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            
        if(computador == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        if(computador.getEspecificacoes().isEmpty() || computador.getJogos().isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        computadorService.createComputador(computador);
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
