package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.demo.model.Fil;

@Repository
public interface FileRepository extends JpaRepository<Fil, Integer> {

    // // Define custom query to find file paths by assignment id
    // @Query("SELECT f.path FROM AssignmentFile af JOIN af.file f WHERE af.assignment.id = ?1")
    // List<String> findPathsByAssignmentId(Long assignmentId);

    // Define custom query to delete files by assignment id
    @Query(value = "DELETE FROM Assignment_Files af WHERE af.a_id = ?1" , nativeQuery = true)
    void deleteByAssignmentId(int assignmentId);
}
