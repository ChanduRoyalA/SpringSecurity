package com.learn.security.controller;


import com.learn.security.model.Student;
import com.learn.security.model.User;
import com.learn.security.repo.UserRepo;
import com.learn.security.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class ProjectController {


    private List<Student> students = new ArrayList<>(List.of(
            new Student("chandu"),
            new Student("xyz")
    ));

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String getMapping(HttpServletRequest request){
        return "hello spring"+ request.getSession().getId() ;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/student")
    public List<Student> getStudents(){
        return students;
    }

    @PostMapping("/student")
    public Student addStudent(@RequestBody Student student){
        students.add(student);
        return student;
    }

    /**
     * User Rest Operations
     */

    @GetMapping("/users")
    public Collection<User> getUsers(){
        Collection<User> allUsers = userService.getAllUsers();
        return allUsers;
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Long id){
        User userById = userService.getUser(id);
        if(userById!=null){
            return userById;
        }
        throw new EntityNotFoundException("User with ID:"+id+" Not Found");
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user){
       return userService.addUser(user);
    }

}
