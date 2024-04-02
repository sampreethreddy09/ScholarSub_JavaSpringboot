package com.demo.controller;

import com.demo.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @GetMapping("/api/sections/{tid}")
    public List<Map<String, Object>> getSections(@PathVariable String tid) {
        return sectionService.findSecIdAndNameByTeacherId(tid);
    }
}
