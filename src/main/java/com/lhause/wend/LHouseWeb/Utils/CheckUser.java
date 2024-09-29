package com.lhause.wend.LHouseWeb.Utils;

import com.lhause.wend.LHouseWeb.data.UserEntity;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author Wendley S
 */
public class CheckUser {
    public static boolean userRoleIsCliente(HttpServletRequest request){
        var user = getUser(request);
        if(user == null)
            return false;
        return user.getTipo().equals("cliente");
    }
    
    public static boolean userIsLogged(HttpServletRequest request){
        var session = request.getSession();      
        try{
            var user = (UserEntity)session.getAttribute("current-user");
            if(user == null)
                return false;
        }catch(Exception ex){
            return false;
        }
        
        return true;
    }
    
    public static boolean userIsNotLogged(HttpServletRequest request){
        return !userIsLogged(request);
    }
    
    public static UserEntity getUser(HttpServletRequest request){
        if(userIsNotLogged(request))
            return null;
        return (UserEntity)request.getSession().getAttribute("current-user");
    }
}
