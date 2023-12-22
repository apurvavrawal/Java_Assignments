package com.josh.hotelmgmt.services;

import com.josh.hotelmgmt.entities.FeedBack;

import java.util.List;

public interface FeedBackService {
    List<FeedBack> getAllFeedback();

    void submitFeedback(FeedBack feedBack);
}
