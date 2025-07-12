package com.learn.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig { // To Configure Our Own Setting of Security

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(customizer->customizer.disable()) // To disable the Csrf Token while doing CURD Operations
                .authorizeHttpRequests(req->req.anyRequest().authenticated()) // Applies Auth for every Request
                //.formLogin(Customizer.withDefaults()) // Adds Login form to the Requests (UI)
                .httpBasic(Customizer.withDefaults()) // Adds Login functionality to APIs (hitting Request from Postman)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //making the http State Less
                .build();  // This will return the Object of SecurityFilterChain
    }
}
