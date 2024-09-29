package com.lhause.wend.LHouseWeb.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Wendley S
 */
@Controller
public class CustomErrorController implements ErrorController{
    
    @GetMapping("/error")
    public String handleError() {     
        return "error";
    }
}
