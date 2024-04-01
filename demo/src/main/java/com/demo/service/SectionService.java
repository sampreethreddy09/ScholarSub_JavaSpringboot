package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.model.Section;
import com.demo.repository.SectionRepository;

import java.util.List;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;


}
