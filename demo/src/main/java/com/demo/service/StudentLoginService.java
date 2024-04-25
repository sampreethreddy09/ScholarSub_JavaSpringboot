package com.demo.service;

import org.springframework.stereotype.Service;

@Service
public class StudentLoginService implements LoginService {
    private final StudentService studentService;

    public StudentLoginService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public boolean login(String id, String password) {
        return studentService.login(id, password);
    }
}
