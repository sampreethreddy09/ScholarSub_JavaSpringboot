package com.demo.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.service.LoginService;
import com.demo.service.TeacherService;

@Component
public class TeacherServiceFactory implements ServiceFactory {
    private final TeacherService teacherService;

    @Autowired
    public TeacherServiceFactory(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Override
    public LoginService createService(int role) {
        if (role == 1) {
            return teacherService;
        }
        throw new IllegalArgumentException("Invalid role for teacher service");
    }
}