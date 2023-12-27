package com.kio.my.service;


import com.kio.my.domain.ApiAdmin;
import com.kio.my.dto.ApiAdminDTO;
import com.kio.my.repository.ApiAdminRepository;
import com.kio.my.util.JWTUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ApiLoginService {

    @Autowired
    ApiAdminRepository apiAdminRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JWTUtil jwtUtil;

    @Value("${api.admin.username}")
    String id;
   @Value("${api.admin.password}")
    String pw;


    public String login(ApiAdminDTO apiAdminDTO) throws Exception{
//        Optional<ApiAdmin> result = apiAdminRepository.findById(apiAdminDTO.getId());
//
//        ApiAdmin admin = result.orElseThrow();
//
//         if(passwordEncoder.matches(apiAdminDTO.getPw(),admin.getMpw())){
//             Map<String,Object> val = new HashMap<>();
//             val.put(admin.getMid(), admin.getMpw());
//             return jwtUtil.generateToken(val,60);
//         } else {
//             throw new Exception("not match");
//         }

        if(apiAdminDTO.getId().equalsIgnoreCase(id)){
            if(apiAdminDTO.getPw().equalsIgnoreCase(pw)){
                Map<String,Object> val = new HashMap<>();
                val.put(apiAdminDTO.getId(),passwordEncoder.encode(apiAdminDTO.getPw()));
                return jwtUtil.generateToken(val,60);
            }
        }

        throw new Exception();


    }
}
