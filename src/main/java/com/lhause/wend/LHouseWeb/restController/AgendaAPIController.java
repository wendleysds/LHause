package com.lhause.wend.LHouseWeb.restController;

import static com.lhause.wend.LHouseWeb.Utils.CheckUser.*;
import com.lhause.wend.LHouseWeb.data.AgendaEntity;
import com.lhause.wend.LHouseWeb.service.AgendaService;
import com.lhause.wend.LHouseWeb.service.ComputadorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
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
@RequestMapping("/api/agenda")
public class AgendaAPIController {
    
    @Autowired
    private AgendaService agendaService;
    
    @Autowired
    private ComputadorService computadorService;
    
    @GetMapping("")
    public ResponseEntity<List<AgendaEntity>> getAllAgenda(){
        return ResponseEntity.ok(agendaService.findAllAgendas());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AgendaEntity> geAgendaById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(agendaService.findAgendaById(id));
    }
    
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<AgendaEntity>> getAgendaByUserId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(agendaService.findAllByAgendaByUserId(id));
    }
    
    @PostMapping("")
    public ResponseEntity createAgenda(HttpServletRequest request, @Valid @RequestBody AgendaEntity agenda){
        
        var user = getUser(request);
        
        if(user == null)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        var computador = computadorService.findComputadorById(agenda.getComputadorId());
        
        if(computador == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        
        agenda.setUsuarioId(user.getId());
        
        agendaService.createAgenda(agenda);       
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAgenda(HttpServletRequest request, @PathVariable Integer id){
        if(userRoleIsCliente(request))
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        
        var agenda = agendaService.findAgendaById(id);
        if(agenda == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
            
        agendaService.deleteAgenda(agenda.getId());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
