package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.model.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    // You can define custom query methods here if needed
    List<Submission> findByStudentId(String studentId);
}