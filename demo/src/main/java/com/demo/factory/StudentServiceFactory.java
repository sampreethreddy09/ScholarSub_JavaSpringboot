package com.demo.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.demo.service.LoginService;
import com.demo.service.StudentService;

@Component
@Primary
public class StudentServiceFactory implements ServiceFactory {
    private final StudentService studentService;

    @Autowired
    public StudentServiceFactory(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public LoginService createService(int role) {
        if (role == 0) {
            return studentService;
        }
        throw new IllegalArgumentException("Invalid role for student service");
    }
}