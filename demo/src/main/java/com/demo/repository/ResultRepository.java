package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Result;



@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
    
}

