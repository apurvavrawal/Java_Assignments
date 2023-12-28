package com.josh.hotelmgmt.services.implementations;

import com.josh.hotelmgmt.entities.Feedback;
import com.josh.hotelmgmt.repositories.FeedBackRepository;
import com.josh.hotelmgmt.services.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackServiceImpl implements FeedBackService {
    @Autowired
    private FeedBackRepository feedBackRepository;
    @Override
    public List<Feedback> getAllFeedback() {
        return feedBackRepository.findAll();
    }

    @Override
    public void submitFeedback(Feedback feedBack) {
        feedBackRepository.save(feedBack);
    }
}
