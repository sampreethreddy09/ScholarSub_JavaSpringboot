package com.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.AssignmentDTO;
import com.demo.dto.AssignmentDetailsDTO;
import com.demo.model.Assignment;
import com.demo.service.AssignmentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping("/assignments/live/{studentId}")
     public List<AssignmentDTO> loadLiveAssignments(@PathVariable String studentId) {
        List<Assignment> assignments = assignmentService.loadLiveAssignments(studentId);
        List<AssignmentDTO> assignmentDTOs = new ArrayList<>();

        for (Assignment assignment : assignments) {
            AssignmentDTO dto = new AssignmentDTO();
            dto.setId(assignment.getId());
            dto.setName(assignment.getName());
            dto.setStartTime(assignment.getStartTime());
            dto.setEndTime(assignment.getEndTime());
            dto.setAllowLateSubmission(assignment.isAllowLateSubmission());
            dto.setScheduleRightNow(assignment.isScheduleRightNow());
            dto.setSecId(assignment.getSection().getId()); // Set only sec_id
            dto.setConstraints(assignment.getConstraints());
            dto.setDescription(assignment.getDescription());
            dto.setMaxMarks(assignment.getMaxMarks());
            dto.setInputFilesThere(assignment.isInputFilesThere());
            dto.setEvaluated(assignment.isEvaluated());
            assignmentDTOs.add(dto);
        }

        return assignmentDTOs;
    }

    @GetMapping("/assignments/past/{studentId}")
    public List<AssignmentDTO> loadPastAssignments(@PathVariable String studentId) {
       List<Assignment> assignments = assignmentService.loadPastAssignments(studentId);
       List<AssignmentDTO> assignmentDTOs = new ArrayList<>();

       for (Assignment assignment : assignments) {
           AssignmentDTO dto = new AssignmentDTO();
           dto.setId(assignment.getId());
           dto.setName(assignment.getName());
           dto.setStartTime(assignment.getStartTime());
           dto.setEndTime(assignment.getEndTime());
           dto.setAllowLateSubmission(assignment.isAllowLateSubmission());
           dto.setScheduleRightNow(assignment.isScheduleRightNow());
           dto.setSecId(assignment.getSection().getId()); // Set only sec_id
           dto.setConstraints(assignment.getConstraints());
           dto.setDescription(assignment.getDescription());
           dto.setMaxMarks(assignment.getMaxMarks());
           dto.setInputFilesThere(assignment.isInputFilesThere());
           dto.setEvaluated(assignment.isEvaluated());
           assignmentDTOs.add(dto);
       }

       return assignmentDTOs;
   }


    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<AssignmentDetailsDTO> loadAssignmentDetails(@PathVariable int assignmentId) {

        AssignmentDetailsDTO assignment = assignmentService.loadAssignmentDetails(assignmentId);
        
        return ResponseEntity.ok(assignment);
    }
}
