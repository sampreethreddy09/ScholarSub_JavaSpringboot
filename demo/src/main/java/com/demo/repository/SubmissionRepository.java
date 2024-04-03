package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.model.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    // You can define custom query methods here if needed
}