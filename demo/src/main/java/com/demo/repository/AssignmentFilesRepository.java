package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.AssignmentFiles;
import com.demo.model.AssignmentFilesId;


import java.util.List;

@Repository
public interface AssignmentFilesRepository extends JpaRepository<AssignmentFiles , AssignmentFilesId> {
    List<AssignmentFiles> findByAssignmentId(int assignmentId);
}
