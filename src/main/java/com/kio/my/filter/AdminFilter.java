package com.kio.my.filter;


import com.kio.my.util.JWTUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@WebFilter("/api/admin/*")
public class AdminFilter implements Filter {



    private final JWTUtil jwtUtil;

    AdminFilter(JWTUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
         Cookie[] cookies = req.getCookies();
        boolean isvalid = false;
        if(cookies != null){
         for(Cookie c: cookies){
             if(c.getName().equalsIgnoreCase("accessToken")){
                isvalid =  jwtUtil.validateToken(c.getValue());
                if(isvalid){
                    chain.doFilter(request,response);
                }
             }
         }
        }
        if(!isvalid){
        HttpServletResponse res = (HttpServletResponse) response;
         res.sendError(401);
        }
    }
}
