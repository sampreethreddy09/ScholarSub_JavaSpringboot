package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.demo.dto.EvaluateSubmissionDTO;
import com.demo.dto.ResultDTO;
import com.demo.model.Student;
import com.demo.service.ResultService;
import com.demo.service.StudentService;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class ResultController {

    @Autowired
    private ResultService resultService;
    private StudentService studentService;

    public ResultController(ResultService resultService, StudentService studentService) {
        this.resultService = resultService;
        this.studentService = studentService;
    }

    @PutMapping("/teacher/evaluate")
    public ResponseEntity<String> evaluateSubmission(@RequestBody EvaluateSubmissionDTO data){
        try {
            resultService.evaluateSubmission(data);
            return ResponseEntity.ok("Result updated successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating result", e);
        }
    }

    @GetMapping("/fetchresult/{sId}/{aId}")
    public ResponseEntity<List<ResultDTO>> fetchResult(@PathVariable("sId") String sId, @PathVariable("aId") String aId) {
        List<ResultDTO> result = resultService.fetchResult(sId, Integer.parseInt(aId));
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/students/notsubmitted/{aId}")
    public List<String> getStudentsNotSubmitted(@PathVariable int aId) {
        return studentService.getStudentsNotSubmitted(aId);
    }

    @GetMapping("/assignment/stats/{assignmentId}")
    public List<Object[]> getAssignmentStats(@PathVariable String assignmentId) {
        return resultService.getAssignmentStats(Integer.parseInt(assignmentId));
    }

}
