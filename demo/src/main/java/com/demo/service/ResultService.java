package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.EvaluateSubmissionDTO;
import com.demo.model.Result;
import com.demo.repository.ResultRepository;

import java.util.List;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

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

}

