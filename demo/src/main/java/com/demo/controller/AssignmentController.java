package com.demo.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.AssignmentDTO;
import com.demo.dto.AssignmentDetailsDTO;
import com.demo.model.Assignment;
import com.demo.service.AssignmentService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping("/teacher/liveassignments/{teacherId}")
     public List<AssignmentDTO> loadLiveAssignmentsByTeacher(@PathVariable String teacherId) {
        List<Assignment> assignments = assignmentService.loadLiveAssignmentsByTeacher(teacherId);
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
            assignmentDTOs.add(dto);
        }

        return assignmentDTOs;
    }

    @GetMapping("/teacher/pastassignments/{teacherId}")
     public List<AssignmentDTO> loadPastAssignmentsByTeacher(@PathVariable String teacherId) {
        List<Assignment> assignments = assignmentService.loadPastAssignmentsByTeacher(teacherId);
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
            assignmentDTOs.add(dto);
        }

        return assignmentDTOs;
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



    @PostMapping("/teacher/postassignment")
    public ResponseEntity<String> uploadAssignmentWithFile(@RequestParam("file") MultipartFile[] files,
                                            @RequestParam("aname") String aname,
                                            @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date endTime,
                                            @RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date startTime,
                                            @RequestParam("allowLateSubmission") boolean allowLateSubmission,
                                            @RequestParam("max_marks") int maxMarks,
                                            @RequestParam("description") String description,
                                            @RequestParam("constraints") String constraints,
                                            @RequestParam("selectedOption") String selectedOption,
                                            @RequestParam("isRytNow") boolean isRytNow,
                                            @RequestParam("isInput") boolean isInput) throws IOException {
        // System.out.println(createAssignmentDTO);
        return assignmentService.uploadAssignmentWithFile(files, aname, endTime, startTime, allowLateSubmission
                                                            , maxMarks, description, constraints, selectedOption, isRytNow,  isInput);
    }

    // @DeleteMapping("/assignment/{assignmentId}")
    // public String deleteAssignment(@PathVariable String assignmentId) {
    //     return assignmentService.deleteAssignment(Integer.parseInt(assignmentId));
    // }
    
}
