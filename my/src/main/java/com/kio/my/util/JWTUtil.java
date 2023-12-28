package com.kio.my.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



@Log4j2
@Component
public class JWTUtil {

    @Value("${org.my.jwt.secret}")
    private String key;

    public String generateToken(Map<String,Object> valueMap, int minutes){

        Map<String, Object> headers = new HashMap<>();
        headers.put("typ","JWT");
        headers.put("alg","HS256");

        Map<String, Object> payloads = new HashMap<>();
        payloads.putAll(valueMap);

        int time = 1 * minutes;

        String jwtStr = Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from((ZonedDateTime.now().plusMinutes(time).toInstant())))
                .signWith(SignatureAlgorithm.HS256, key.getBytes())
                .compact();


        return jwtStr;
    }

    public boolean validateToken(String token) throws JwtException {
        Map<String, Object> claim = null;

        try{
        claim = Jwts.parser()
                .setSigningKey(key.getBytes())
                .parseClaimsJws(token)
                .getBody();

        } catch (JwtException e){
            claim = null;
        }

        return claim == null? false : true;
    }

    public  String resolveToken(HttpServletRequest request){
      return null;

    }
}
