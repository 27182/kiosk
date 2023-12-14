package com.kio.my.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class HomeController {

    @GetMapping("/")
    public String home(Model model){
        log.info("welcome to my home.....");

        model.addAttribute("msg","Hi, there!");

        return "/home";

    }

    @GetMapping("/boot")
    public String boot(Model model){

        return "/boot";
    }


}
