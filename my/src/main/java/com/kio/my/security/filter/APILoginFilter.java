package com.kio.my.security.filter;


import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

@Log4j2
public class APILoginFilter extends AbstractAuthenticationProcessingFilter {

    public APILoginFilter(String defaultFilterProcessUrl){
        super(defaultFilterProcessUrl);
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if(request.getMethod().equalsIgnoreCase("GET")){
            return null;
        }


        Map<String,String> jsonData = parseRequestJSON(request);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                jsonData.get("mid"),jsonData.get("mpw")
        );

        return getAuthenticationManager().authenticate(authenticationToken);
    }


    private Map<String,String> parseRequestJSON(HttpServletRequest request){
        try(Reader reader = new InputStreamReader(request.getInputStream())){
            Gson gson = new Gson();

            return  gson.fromJson(reader, Map.class);
        } catch (Exception e){
            log.error(e.getMessage());
        }

        return null;
    }
}
