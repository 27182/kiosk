package com.kio.my.util;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

@SpringBootTest
@Log4j2
public class JWTUtilTest {
    @Autowired
    private JWTUtil jwtUtil;

    @Test
    public void testGenerate() {
        Map<String, Object> claimMap = Map.of("mid","ABCDE");


        log.info(Date.from((ZonedDateTime.now().plusMinutes(1).toInstant())));

        String jwtStr = jwtUtil.generateToken(claimMap,1);

        log.info(jwtStr);
    }


    public void testValidate() {
        String jwtStr = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MDM2Mzg3MzYsIm1pZCI6IkFCQ0RFIiwiaWF0IjoxNzAzNjM4Njc2fQ.aJiPmNuJom7RcdCCqw0DdXI6rt54YjGzS9skpiQ9d8g";


    }

}
