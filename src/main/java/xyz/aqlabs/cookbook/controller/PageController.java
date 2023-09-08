package xyz.aqlabs.cookbook.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/v1")
public class PageController {


    @GetMapping("/login")
    public String getLoginForm(){return "login.html";}


    @GetMapping("/register")
    public String getRegisterForm(){return "register.html";}


    @GetMapping("/landing")
    public String getLandingPage(){return "landing.html";}


    @GetMapping("/main")
    public String getMainPage(){return "main.html";}


    @GetMapping("/cookbook")
    public String getCookBookPage(@RequestParam("cookBookId")Integer cookBookId, Model model){
        model.addAttribute("cookBookId", cookBookId);
        return "cookbook";}

    @GetMapping("/tool/conversion")
    public String getConversionPage(){
        return "conversion.html";
    }

}
