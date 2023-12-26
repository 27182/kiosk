package com.kio.my.config;


import com.kio.my.security.APIAdminDetailService;
import com.kio.my.security.filter.APILoginFilter;
import com.kio.my.security.handler.APILoginSuccessHandler;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Log4j2
@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class CustomSecurityConfig {

    @Autowired
    private  final APIAdminDetailService apiAdminDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        log.info("---------------config----------------");
//
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//
//        authenticationManagerBuilder
//                .userDetailsService(apiAdminDetailService)
//                .passwordEncoder(passwordEncoder());
//
//        AuthenticationManager authenticationManager =
//                authenticationManagerBuilder.build();
//
//        http.authenticationManager(authenticationManager);
//
//        APILoginFilter apiLoginFilter = new APILoginFilter("/generateToken");
//        apiLoginFilter.setAuthenticationManager(authenticationManager);
//
//        APILoginSuccessHandler successHandler = new APILoginSuccessHandler();
//        apiLoginFilter.setAuthenticationSuccessHandler(successHandler);
//
//        http.addFilterBefore(apiLoginFilter, UsernamePasswordAuthenticationFilter.class);
//
//        http.csrf(AbstractHttpConfigurer::disable);
//
//        http.sessionManagement((a)->{
//                a.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                });




        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        log.info("-------------------------------web configure-------------------------");
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
