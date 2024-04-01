package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.repository.SectionRepository;


@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;


}
