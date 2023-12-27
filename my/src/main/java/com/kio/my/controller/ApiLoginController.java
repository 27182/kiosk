package com.kio.my.controller;


import com.kio.my.domain.ApiAdmin;
import com.kio.my.dto.ApiAdminDTO;
import com.kio.my.service.ApiLoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
public class ApiLoginController {

    @Autowired
    ApiLoginService apiLoginService;


    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/login")
    @GetMapping("/login/pass")
    public Map<String,Object> login(@RequestBody ApiAdminDTO apiAdminDTO, HttpServletResponse res) {

        Map<String, Object> result = new HashMap<>();

        if(리퀘스트url)
        try {
            String token = apiLoginService.login(apiAdminDTO);
            result.put("isSuccess",true);
//            Cookie cookie = new Cookie("accesToken",token);
//            cookie.setMaxAge(60 * 60);
//            cookie.setHttpOnly(true);
//            cookie.setPath("/admin");
//            cookie.set
//            res.addCookie(cookie);
              res.setHeader("Set-Cookie", "accessToken=" + token+"; HttpOnly; SameSite=strict; max-age=3600; path=/admin");
              Cookie cookie = new Cookie("Login","true");
              cookie.setMaxAge(60 * 60);
              res.addCookie(cookie);
        } catch (Exception e) {
            result.put("isSuccess",false);
        }

        return result;

    }


    @PostMapping("/logout")
    public  void logout(HttpServletResponse res){
        res.setHeader("Set-Cookie", "accessToken=null ; HttpOnly; SameSite=strict; max-age=0; path=/admin");
        Cookie cookie = new Cookie("Login","false");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
    }

}
