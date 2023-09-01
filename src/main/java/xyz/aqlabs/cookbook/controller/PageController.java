package xyz.aqlabs.cookbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1")
public class PageController {

    @GetMapping("/loginRegister")
    public String getLoginRegister(){
        return "login-register.html";
    }
}
