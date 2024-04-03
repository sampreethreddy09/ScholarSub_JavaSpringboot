package com.demo.repository;

import com.demo.model.Section;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface SectionRepository extends JpaRepository<Section, String> {

    @Query("SELECT s.id FROM Section s WHERE s.teacher.id = :teacherId")
    List<String> findSectionIdsByTeacherId(@Param("teacherId") String teacherId);

    @Query(value = "SELECT s.sec_id AS secId, s.name AS secName FROM section s WHERE s.t_id = :teacherId", nativeQuery = true)
    List<Map<String, Object>> findSecIdAndNameByTeacherId(@Param("teacherId") String teacherId);
}

