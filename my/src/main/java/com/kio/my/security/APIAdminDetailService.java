package com.kio.my.security;

import com.kio.my.domain.APIAdmin;
import com.kio.my.dto.APIAdminDTO;
import com.kio.my.repository.APIAdminRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Log4j2
@Service
public class APIAdminDetailService implements UserDetailsService {

    private final APIAdminRepository apiAdminRepository;

    private final PasswordEncoder passwordEncoder;

    public APIAdminDetailService(APIAdminRepository apiAdminRepository){
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.apiAdminRepository = apiAdminRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("loadUserByUsername: "+ username);

        Optional<APIAdmin> result = apiAdminRepository.findById(username);

        APIAdmin apiAdmin = result.orElseThrow(()->new UsernameNotFoundException("Cannot find user id"));

        APIAdminDTO dto = new APIAdminDTO(apiAdmin.getMid(),apiAdmin.getMpw(), List.of(new SimpleGrantedAuthority("ADMIN")));


        return dto;
    }
}
