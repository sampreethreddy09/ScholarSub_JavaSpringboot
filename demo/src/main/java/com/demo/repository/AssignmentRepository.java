package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.model.Assignment;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    List<Assignment> findBySectionIdIn(List<String> sectionIds);

    // Define custom query to delete assignment by id
    @Modifying
    @Query("DELETE FROM Assignment a WHERE a.id = ?1")
    void deleteById(Long assignmentId);
}
