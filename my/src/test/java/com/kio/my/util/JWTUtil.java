package com.kio.my.util;


import io.jsonwebtoken.JwtException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Log4j2
public class JWTUtil {
    @Value("${org.my.jwt.secret}")
    private String key;

    public String generateToken(Map<String, Object> valueMap, int days){
        log.info("generate key.... "+ key);

        return null;
    }

    public Map<String, Object> validateToken(String token) throws JwtException {

        Map<String, Object> claim = null;

        return claim;
    }

}
