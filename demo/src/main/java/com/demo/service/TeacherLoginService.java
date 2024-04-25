package com.demo.service;

import org.springframework.stereotype.Service;

@Service
public class TeacherLoginService implements LoginService {
    private final TeacherService teacherService;

    public TeacherLoginService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Override
    public boolean login(String id, String password) {
        return teacherService.login(id, password);
    }
}

