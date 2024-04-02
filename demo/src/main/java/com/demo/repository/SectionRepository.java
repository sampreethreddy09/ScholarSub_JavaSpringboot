package com.demo.repository;

import com.demo.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SectionRepository extends JpaRepository<Section, String> {

}

