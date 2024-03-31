package com.demo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.demo.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, String>{
    Teacher findByIdAndPassword(String id, String password);
}
