package com.demo.service;

import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class SubmissionService {

    public long createSubmission(String s_id, String a_id, Date tos) {
        // Implement logic to create submission record in database and return the submission ID
        return 0; // Replace 0 with the actual submission ID
    }

    public void attachFileToSubmission(long submissionId, long fileId) {
        // Implement logic to attach file to submission record in database
    }
}
