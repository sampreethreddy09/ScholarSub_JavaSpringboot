package com.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.repository.SectionRepository;


@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    public List<Map<String, Object>> findSecIdAndNameByTeacherId(String tId) {
        return sectionRepository.findSecIdAndNameByTeacherId(tId);
    }


}
