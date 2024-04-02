package com.demo.repository;

import com.demo.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, String> {

    @Query("SELECT s.id FROM Section s WHERE s.teacher.id = :teacherId")
    List<String> findSectionIdsByTeacherId(@Param("teacherId") String teacherId);
}

