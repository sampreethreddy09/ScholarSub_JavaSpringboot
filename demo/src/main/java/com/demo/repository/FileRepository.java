package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.File;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
    // You can define custom query methods here if needed
}
