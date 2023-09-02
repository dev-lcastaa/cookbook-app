package xyz.aqlabs.cookbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/v1")
public class PageController {

    @GetMapping("/loginRegister")
    public String getLoginRegister(){
        return "login-register.html";
    }

    @GetMapping("/account")
    public String getAccountPage(){return "account.html";}

    @GetMapping("/cookbook")
    public String getCookBookPage(@RequestParam("cookBookId")Integer cookBookId, Model model){
        model.addAttribute("cookBookId", cookBookId);
        return "cookbook";}
}
