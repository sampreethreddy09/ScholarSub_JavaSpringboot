package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.demo.model.Fil;

@Repository
public interface FileRepository extends JpaRepository<Fil, Integer> {

    // // Define custom query to find file paths by assignment id
    // @Query("SELECT f.path FROM AssignmentFile af JOIN af.file f WHERE af.assignment.id = ?1")
    // List<String> findPathsByAssignmentId(Long assignmentId);

    // // Define custom query to delete files by assignment id
    // @Modifying
    // @Query("DELETE FROM AssignmentFile af WHERE af.assignment.id = ?1")
    // void deleteByAssignmentId(Long assignmentId);
}
