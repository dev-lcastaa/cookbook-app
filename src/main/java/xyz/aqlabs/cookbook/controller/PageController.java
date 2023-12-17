package xyz.aqlabs.cookbook.controller;

/*
The Page controller is responsible for managing what Html template gets returned based on what endpoint hit
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ui")
public class PageController {

    // Implemented logging to track method activity
    private static final Logger LOGGER = LoggerFactory.getLogger(PageController.class);


    // GET request endpoint to get login HTML Page.
    @GetMapping("/login")
    public String getLoginForm(){
        LOGGER.info("[o][o][o]---| Method INVOKED in Page Controller|---[o][o][o]");
        LOGGER.info("[o][o][o]---| getLoginForm() |---[o][o][o]");
        return "login.html";
    }


    // GET request endpoint to get register HTML Page.
    @GetMapping("/register")
    public String getRegisterForm(){
        LOGGER.info("[o][o][o]---| Method INVOKED in Page Controller|---[o][o][o]");
        LOGGER.info("[o][o][o]---| getRegisterForm() |---[o][o][o]");
        return "register.html";
    }


    // GET request endpoint to get Landing HTML Page.
    @GetMapping("/landing")
    public String getLandingPage(){
        LOGGER.info("[o][o][o]---| Method INVOKED in Page Controller|---[o][o][o]");
        LOGGER.info("[o][o][o]---| getLandingPage() |---[o][o][o]");
        return "landing.html";
    }


    // GET request endpoint to get Main HTML Page.
    @GetMapping("/main")
    public String getMainPage(){
        LOGGER.info("[o][o][o]---| Method INVOKED in Page Controller|---[o][o][o]");
        LOGGER.info("[o][o][o]---| getMainPage() |---[o][o][o]");
        return "main.html";
    }


    // GET request endpoint to get CookBook HTML Page.
    @GetMapping("/cookbook")
    public String getCookBookPage(@RequestParam("cookBookId")Integer cookBookId, Model model){
        LOGGER.info("[o][o][o]---| Method INVOKED in Page Controller|---[o][o][o]");
        LOGGER.info("[o][o][o]---| getCookBookPage() |---[o][o][o]");
        model.addAttribute("cookBookId", cookBookId);
        return "cookbook";
    }


    // GET request endpoint to get Conversion HTML Page.
    @GetMapping("/conversion")
    public String getConversionPage(){
        LOGGER.info("[o][o][o]---| Method INVOKED in Page Controller|---[o][o][o]");
        LOGGER.info("[o][o][o]---| getConversionPage() |---[o][o][o]");
        return "conversion.html";
    }


    // GET request endpoint to get Finder HTML Page.
    @GetMapping("/recipeFinder")
    public String getFinderPage(){
        LOGGER.info("[o][o][o]---| Method INVOKED in Page Controller|---[o][o][o]");
        LOGGER.info("[o][o][o]---| getFinderPage() |---[o][o][o]");
        return "finder.html";
    }

}
