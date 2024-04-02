package com.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.ResultDTO;
import com.demo.service.ResultService;

import java.util.List;

@RestController
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping("/api/fetchresult/{sId}/{aId}")
    public ResponseEntity<List<ResultDTO>> fetchResult(@PathVariable("sId") int sId, @PathVariable("aId") int aId) {
        List<ResultDTO> result = resultService.fetchResult(sId, aId);
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
