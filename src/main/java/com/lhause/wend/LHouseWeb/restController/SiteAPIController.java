package com.lhause.wend.LHouseWeb.restController;

import com.lhause.wend.LHouseWeb.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Wendley S
 */
@RestController
@RequestMapping("/api")
public class SiteAPIController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity login(HttpServletRequest request, @RequestBody Map<String, Object> loginData){
        
        var user = userService.findUser((String)loginData.get("login"), (String)loginData.get("password"));
        
        if(user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else{
            var session = request.getSession();       
            session.setAttribute("current-user", user);
            
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
    }
}
