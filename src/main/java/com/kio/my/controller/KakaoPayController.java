package com.kio.my.controller;


import com.kio.my.domain.Ordered;
import com.kio.my.dto.OrderDTO;
import com.kio.my.dto.ReadyResponseDTO;
import com.kio.my.repository.OrderRepository;
import com.kio.my.service.KakaoPayServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Controller
@Log4j2
@RequiredArgsConstructor
public class KakaoPayController {

    @Autowired
    KakaoPayServiceImpl kakaoPayServiceImpl;

    @Autowired
    OrderRepository orderRepository;

    @Value("${PRESENT_SERVICE_URL}")
    private String present_url;



    @PostMapping("api/kakaopay/ready")
    @ResponseBody
    public ReadyResponseDTO readytoPay(@RequestBody OrderDTO orderDTO){
         return kakaoPayServiceImpl.readytoPay(orderDTO);
    }


    @GetMapping("/approve/{ono}")
    public String ApprovePayment(@RequestParam(name = "pg_token") String pg_token, @PathVariable(name = "ono") String ono, Model model) throws IOException {


        log.info(pg_token);
        log.info(ono);
        try{

         Ordered ordered = kakaoPayServiceImpl.approvePayment(pg_token,ono);
         ordered.confirmPaid(1);
         orderRepository.save(ordered);
         model.addAttribute("order",ordered);
         
         return "PaymentSuccess";
        } catch (Exception e){
            
            return null;
        }
    }

    @GetMapping("failtopay")
    public String handleFail(){
        return "PaymentFail";
    }



}
