package com.demo.service;

import org.springframework.stereotype.Service;

import com.demo.dto.EvaluateSubmissionDTO;
import com.demo.model.Result;
import com.demo.dto.ResultDTO;
import com.demo.repository.ResultRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {

    private final ResultRepository resultRepository;
    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public void evaluateSubmission(EvaluateSubmissionDTO data) {
        int subId = data.getSubId();
        System.out.println(subId);
        int obtainedMarks = data.getObtainedMarks();
        String feedback = data.getFeedback();

        System.out.println("im hre");
        // Update the result in the database
        resultRepository.findById(subId).ifPresent(result -> {
            result.setObtainedMarks(obtainedMarks);
            result.setFeedback(feedback);
            System.out.println("in here");
            resultRepository.save(result);
        });
    }

    public List<ResultDTO> fetchResult(String sId, int aId) {
    List<Object[]> results = resultRepository.fetchResult(sId, aId);
    List<ResultDTO> dtos = new ArrayList<>();
    for (Object[] result : results) {
        ResultDTO dto = new ResultDTO();
        dto.setSubmissionId((Integer) result[0]);
        dto.setTos((LocalDateTime) result[1]);
        dto.setAssignmentId((Integer) result[2]);
        dto.setStudentId((String) result[3]);
        dto.setObtainedMarks((Integer) result[4]);
        dto.setFeedback((String) result[5]);
        dto.setMaxMarks((Integer) result[6]);
        dtos.add(dto);
    }
    return dtos;
}

}
