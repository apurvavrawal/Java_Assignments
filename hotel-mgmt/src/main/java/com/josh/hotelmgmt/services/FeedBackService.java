package com.josh.hotelmgmt.services;

import com.josh.hotelmgmt.entities.Feedback;

import java.util.List;

public interface FeedBackService {
    List<Feedback> getAllFeedback();

    void submitFeedback(Feedback feedBack);
}
