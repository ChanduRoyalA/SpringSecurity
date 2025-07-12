package com.learn.security.service;


import com.learn.security.model.User;
import com.learn.security.model.UserPrincipal;
import com.learn.security.repo.UserRepo;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);

        if(user==null){
            System.out.println("User not Found");
            throw new UsernameNotFoundException("User Not Found");
        }

        return new UserPrincipal(user);
    }
}
