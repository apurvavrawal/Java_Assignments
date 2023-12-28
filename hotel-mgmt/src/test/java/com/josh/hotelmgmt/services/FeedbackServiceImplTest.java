package com.josh.hotelmgmt.services;

import com.josh.hotelmgmt.entities.Feedback;
import com.josh.hotelmgmt.repositories.FeedBackRepository;
import com.josh.hotelmgmt.services.implementations.FeedBackServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FeedbackServiceImplTest {
    @Mock
    private FeedBackRepository feedBackRepository;

    @InjectMocks
    private FeedBackServiceImpl feedBackServiceImpl;

    @Test
    void testGetAllFeedback() {
        when(feedBackRepository.findAll()).thenReturn(List.of(new Feedback(), new Feedback()));
        assertEquals(2, feedBackServiceImpl.getAllFeedback().size());
    }

    @Test
    void testSubmitFeedback() {
        Feedback feedback = new Feedback();
        when(feedBackRepository.save(feedback)).thenReturn(feedback);
        assertDoesNotThrow(() -> feedBackServiceImpl.submitFeedback(feedback));
        verify(feedBackRepository).save(feedback);
    }
}
