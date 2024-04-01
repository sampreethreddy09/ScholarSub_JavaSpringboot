// package com.demo.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.demo.model.Result;
// import com.demo.service.ResultService;

// import java.util.List;

// @RestController
// @RequestMapping("/api/results")
// public class ResultController {

//     @Autowired
//     private ResultService resultService;

//     @GetMapping("/fetchresult/{s_id}/{a_id}")
//     public List<Result> fetchResult(@PathVariable("s_id") String s_id, @PathVariable("a_id") String a_id) {
//         return resultService.fetchResult(s_id, a_id);
//     }
// }
