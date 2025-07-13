package com.learn.security.config;


import com.learn.security.service.MyUserDetailsService;
import com.learn.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig { // To Configure Our Own Setting of Security


    @Autowired
    private MyUserDetailsService userDetailsService;

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

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // No Password Encoder to authorize the user
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12)); // using Bcrypt Password Encoder to authorize the user
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }


//    Manually Creating Users
//    @Bean
//    public UserDetailsService userDetailsService(){
//
//
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("maxi")
//                .password("123")
//                .roles("USER")
//                .build();
//
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("dxtr")
//                .password("123")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1,user2);
//    }
}
