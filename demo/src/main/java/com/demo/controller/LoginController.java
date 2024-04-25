package com.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.LoginRequest;
import com.demo.factory.ServiceFactory;
import com.demo.service.LoginService;
import com.demo.service.StudentService;
import com.demo.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@RestController
@RequestMapping("/api")
public class LoginController {
    private final ServiceFactory studentServiceFactory;
    private final ServiceFactory teacherServiceFactory;

    public LoginController(@Qualifier("studentServiceFactory") ServiceFactory studentServiceFactory,
                           @Qualifier("teacherServiceFactory") ServiceFactory teacherServiceFactory) {
        this.studentServiceFactory = studentServiceFactory;
        this.teacherServiceFactory = teacherServiceFactory;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String id = loginRequest.getUname();
        String password = loginRequest.getPswd();
        int role = loginRequest.getRole();
        
        // Create the appropriate service based on the role using the factory
        LoginService loginService;
        if (role == 0) {
            loginService = studentServiceFactory.createService(role);
        } else if (role == 1) {
            loginService = teacherServiceFactory.createService(role);
        } else {
            throw new IllegalArgumentException("Invalid role");
        }

        boolean loggedIn = loginService.login(id, password);

        if (loggedIn) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}