package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.model.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, String>{
    Student findByIdAndPassword(String id, String password);

    @Query(value = "CALL ListStudentsWithoutSubmission(:aId)", nativeQuery = true)
    List<Student> findStudentsWithoutSubmission(@Param("aId") Long aId);
}
