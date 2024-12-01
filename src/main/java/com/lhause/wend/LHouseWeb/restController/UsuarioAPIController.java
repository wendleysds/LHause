package com.lhause.wend.LHouseWeb.restController;

import static com.lhause.wend.LHouseWeb.Utils.CheckUser.*;
import com.lhause.wend.LHouseWeb.Utils.Cpf;
import com.lhause.wend.LHouseWeb.Utils.DatabaseUtils;
import com.lhause.wend.LHouseWeb.data.UserEntity;
import com.lhause.wend.LHouseWeb.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/usuario")
public class UsuarioAPIController {
    
    private static Pattern onlyNumberPattern = Pattern.compile("\\d+");
    private static Pattern foneNumberPatter = Pattern.compile("^\\d{2}[ ]\\d{4,5}[ ]\\d{4}$");
    private static Pattern emailPatter = Pattern.compile("^[A-Za-z0-9._%+-]+[@][A-Za-z0-9.-]+[.][A-Za-z]{2,}$");
    
    @Autowired
    private UserService userService;
    
    @GetMapping("")
    public ResponseEntity<List<UserEntity>> getAllUsers(HttpServletRequest request){
        if(userRoleIsCliente(request))
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        
        return ResponseEntity.ok(userService.findAllUsers());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(HttpServletRequest request, @PathVariable("id") Integer id){
        if(userRoleIsCliente(request))
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        
        var user = userService.findUserById(id);
        if(user == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(user);
    }
    
    @PostMapping
    public ResponseEntity createUser(@RequestBody UserEntity user){     
        if(user == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        
        var cpfFormated = removeCharacters(user.getCpf(), ".-");
        if(!Cpf.cpfValido(cpfFormated) || !onlyNumberPattern.matcher(cpfFormated).matches())
            return new ResponseEntity("cpf invalido",HttpStatus.BAD_REQUEST);
        
        String phoneFormated = removeCharacters(user.getTelefone(), " ()-");
        if(!onlyNumberPattern.matcher(cpfFormated).matches() || phoneFormated.length() < 10 || phoneFormated.length() > 11){
            return new ResponseEntity("numero de telefone invalido",HttpStatus.BAD_REQUEST);
        }
        
        if (phoneFormated.length() <= 10) {
            phoneFormated = phoneFormated.replaceFirst("(.{2})(.{4})(.{4})", "$1 $2 $3");
        } else {
            phoneFormated = phoneFormated.replaceFirst("(.{2})(.{5})(.{4})", "$1 $2 $3");
        }
        
        if(!foneNumberPatter.matcher(phoneFormated).matches()){
            return new ResponseEntity("numero de telefone invalido (formatacao incorreta)",HttpStatus.BAD_REQUEST);
        }
        
        if(!emailPatter.matcher(user.getEmail()).matches()){
            return new ResponseEntity("email invalido",HttpStatus.BAD_REQUEST);
        }
        
        user.setId(null);
        user.setTipo("cliente");
        user.setUltimoLogin(Date.valueOf(LocalDate.now()));
        user.setCpf(cpfFormated);
        user.setTelefone(phoneFormated);
        
        try{
            userService.createUser(user);
        } catch(DataIntegrityViolationException ex){
            String key =  DatabaseUtils.getUniqueKeyNameFromException(ex);
            String message = "'" + key + "' ja utilizado por outro usuario";
             
            return new ResponseEntity(message,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(HttpServletRequest request, @RequestBody UserEntity userNew, @PathVariable("id") Integer id){
        if(userRoleIsCliente(request))
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        
        var userOld = userService.findUserById(id);
        if(userOld == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
     
        return ResponseEntity.ok(userService.updateUser(id, userNew));
    }
    
    private String removeCharacters(String text, String remove){
        String regex = "["+remove+"]";
        return text.replaceAll(regex, "");
    }
}
