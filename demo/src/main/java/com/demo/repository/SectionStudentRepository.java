package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.model.SectionStudent;
import com.demo.model.SectionStudentId;

@Repository
public interface SectionStudentRepository extends JpaRepository<SectionStudent, SectionStudentId> {
    // Define custom query methods if needed
    @Query("SELECT ss.section.id FROM SectionStudent ss WHERE ss.student.id = :studentId")
    List<String> findSectionIdsByStudentId(@Param("studentId") String studentId);
}
