package com.josh.hotelmgmt.controllers;

import com.josh.hotelmgmt.entities.FeedBack;
import com.josh.hotelmgmt.services.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedBackController {
    @Autowired
    private FeedBackService feedBackService;

    // Returns All Feedbacks
    @GetMapping("/")
    public List<FeedBack> getAllFeedBacks(){
        return feedBackService.getAllFeedback();
    }

    @PostMapping("/")
    public ResponseEntity<String> submitFeedback(@RequestBody FeedBack feedBack) {
        feedBackService.submitFeedback(feedBack);
        return new ResponseEntity<>("Feedback submitted successfully !", HttpStatus.CREATED);
    }
}
