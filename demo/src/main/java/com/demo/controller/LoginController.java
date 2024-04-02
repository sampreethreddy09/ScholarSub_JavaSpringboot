package com.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.LoginRequest;
import com.demo.service.StudentService;
import com.demo.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping

public class LoginController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String id = loginRequest.getUname();
        String password = loginRequest.getPswd();
        int role = loginRequest.getRole();
        boolean loggedIn = false;
        if(role == 0){
            loggedIn = studentService.login(id, password);
        }
        else if (role == 1) {
            loggedIn = teacherService.login(id, password);
        }

        if (loggedIn) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
