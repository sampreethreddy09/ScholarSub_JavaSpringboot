package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.model.Fil;

@Repository
public interface FileRepository extends JpaRepository<Fil, Integer> {
}
