package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Teacher;
import com.demo.repository.TeacherRepository;
@Service
public class TeacherService implements LoginService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public boolean login(String id, String password) {
        Teacher teacher = teacherRepository.findByIdAndPassword(id, password);
        return teacher != null;
    }
}

