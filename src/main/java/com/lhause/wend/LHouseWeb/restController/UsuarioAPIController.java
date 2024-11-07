package com.lhause.wend.LHouseWeb.restController;

import static com.lhause.wend.LHouseWeb.Utils.CheckUser.*;
import com.lhause.wend.LHouseWeb.data.UserEntity;
import com.lhause.wend.LHouseWeb.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
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
    //phone (xx) xxxxx-xxxx or (xx) xxxx-xxxx or (xx) xxxx xxxx or (xx) xxxxx xxxx
    //email text@text
    
    @Autowired
    private UserService userService;
    
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
        var cpfLenght = cpfFormated.length();
        if(cpfLenght <= 0 || cpfLenght > 11 || !onlyNumberPattern.matcher(cpfFormated).matches())
            return new ResponseEntity("cpf invalido",HttpStatus.BAD_REQUEST);
        
        //verify phoneNum 2
        //verify email 3
        //verify login 4
        
        return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
        
        //save in database  
        
        //return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(HttpServletRequest request, @RequestBody UserEntity userNew, @PathVariable("id") Integer id){
        if(userRoleIsCliente(request))
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        
        var userOld = userService.findUserById(id);
        if(userOld == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        
        //update
        
        return null;
    }
    
    private String removeCharacters(String text, String remove){
        String regex = "["+remove+"]";
        return text.replaceAll(regex, "");
    }
}
