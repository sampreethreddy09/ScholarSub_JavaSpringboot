package com.demo.service;

import com.demo.dto.EvaluateSubmissionDTO;
import com.demo.dto.ResultDTO;
import com.demo.repository.ResultRepository;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

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
    resultRepository
      .findById(subId)
      .ifPresent(result -> {
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
      dto.setTos(new Date(((Timestamp) result[1]).getTime()));
      dto.setAssignmentId((Integer) result[2]);
      dto.setStudentId((String) result[3]);
      
      // Check if result[4] is null before invoking intValue()
      Integer obtainedMarks = (result[4] != null) ? (Integer) result[4] : null;
      dto.setObtainedMarks(obtainedMarks);

      dto.setFeedback((String) result[5]);
      dto.setMaxMarks((Integer) result[6]);
      dtos.add(dto);
    }
    return dtos;
  }


  public List<Object[]> getAssignmentStats(int assignmentId) {
    return resultRepository.getAssignmentStats(assignmentId);
  }

}
