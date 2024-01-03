package com.kio.my.controller;


import com.kio.my.domain.Ordered;
import com.kio.my.dto.OrderDTO;
import com.kio.my.dto.ReadyResponseDTO;
import com.kio.my.service.KakaoPayServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
public class KakaoPayController {

    @Autowired
    KakaoPayServiceImpl kakaoPayServiceImpl;

    @Value("${PRESENT_SERVICE_URL}")
    private String present_url;



    @PostMapping("api/kakaopay/ready")
    public ReadyResponseDTO readytoPay(@RequestBody OrderDTO orderDTO){
         return kakaoPayServiceImpl.readytoPay(orderDTO);
    }


    @GetMapping("/approve/{ono}")
    public Ordered ApprovePayment(@RequestParam String pg_token, @PathVariable String ono, HttpServletResponse res) throws IOException {


        log.info(pg_token);
        log.info(ono);
        try{

         Ordered ordered = kakaoPayServiceImpl.approvePayment(pg_token,ono);
         res.addCookie(new Cookie("ono", ordered.getOno().toString()));
         res.sendRedirect(present_url + "/result");
         return ordered;
        } catch (Exception e){
            res.sendRedirect(present_url);
        }
    }



}
