package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.demo.model.Fil;

@Repository
public interface FileRepository extends JpaRepository<Fil, Integer> {

    @Query(value = "SELECT f.path FROM Assignment_Files af JOIN file f ON af.f_id = f.f_id WHERE af.a_id = ?1", nativeQuery = true)
    List<String> findPathsByAssignmentId(int assignmentId);
    

    // Define custom query to delete files by assignment id
    @Modifying
    @Query(value = "DELETE FROM Assignment_Files af WHERE af.a_id = ?1" , nativeQuery = true)
    void deleteByAssignmentId(int assignmentId);
}
