package com.kio.my.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class HomeController {

    @Value("${file.upload.directory}")
    String path;

//    @GetMapping("/")
//    public String home(Model model){
//        log.info("welcome to my home.....");
//
//        return "/index";
//
//    }


    @GetMapping("/api/image")
    public String sendImageSource(){
        return path;
    }


}
