package com.learn.security;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Demo {


    private List<Student> students = new ArrayList<>(List.of(
            new Student("chandu"),
            new Student("xyz")
    ));


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
}
