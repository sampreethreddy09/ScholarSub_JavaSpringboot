package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.SubmissionFiles;
import com.demo.model.SubmissionFilesId;

@Repository
public interface SubmissionFilesRepository extends JpaRepository<SubmissionFiles , SubmissionFilesId> {
    List<SubmissionFiles> findBySubmissionId(int assignmentId);
}
