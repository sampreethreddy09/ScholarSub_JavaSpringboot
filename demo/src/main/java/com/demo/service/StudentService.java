package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Student;
import com.demo.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public boolean login(String id, String password){
        Student student = studentRepository.findByIdAndPassword(id, password);
        return student != null;
    }
}
