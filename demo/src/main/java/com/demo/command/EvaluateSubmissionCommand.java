package com.demo.command;

import com.demo.dto.EvaluateSubmissionDTO;
import com.demo.service.ResultService;
import com.demo.service.StudentService;

public class EvaluateSubmissionCommand implements Command {

    private final ResultService resultService;
    private final EvaluateSubmissionDTO data;
    private final StudentService studentService;

    public EvaluateSubmissionCommand(ResultService resultService, EvaluateSubmissionDTO data, StudentService studentService) {
        this.resultService = resultService;
        this.data = data;
        this.studentService = studentService;
    }

    @Override
    public void execute() {
        resultService.evaluateSubmission(data);
    }
}
