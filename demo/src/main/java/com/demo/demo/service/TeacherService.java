package com.demo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.demo.model.Teacher;
import com.demo.demo.repository.TeacherRepository;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public boolean login(String id, String password){
        Teacher teacher = teacherRepository.findByIdAndPassword(id, password);
        return teacher != null;
    }
}
