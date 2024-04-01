package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, String>{
    Teacher findByIdAndPassword(String id, String password);
}
