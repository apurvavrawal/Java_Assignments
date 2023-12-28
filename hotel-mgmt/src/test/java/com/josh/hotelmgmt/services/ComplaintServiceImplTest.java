package com.josh.hotelmgmt.services;

import com.josh.hotelmgmt.entities.Complaint;
import com.josh.hotelmgmt.repositories.ComplaintRepository;
import com.josh.hotelmgmt.services.implementations.ComplaintServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ComplaintServiceImplTest {

    @Mock
    private ComplaintRepository complaintRepository;

    @InjectMocks
    private ComplaintServiceImpl complaintServiceImpl;

    @Test
    void testGetAllComplaints() {
        when(complaintRepository.findAll()).thenReturn(List.of(new Complaint(), new Complaint()));
        assertEquals(2, complaintServiceImpl.getAllComplaints().size());
    }

    @Test
    void testSubmitComplaint() {
        Complaint complaint = new Complaint();
        complaint.setComplaintId(1);
        complaint.setCustomerName("Apurva");
        complaint.setDescription("Food is not so good");
        when(complaintRepository.save(new Complaint())).thenReturn(complaint);
        assertDoesNotThrow(() -> complaintServiceImpl.submitComplaint(complaint));
        verify(complaintRepository).save(complaint);
    }

    @Test
    void testGetComplaintById() {
        when(complaintRepository.findById(anyLong())).thenReturn(Optional.of(new Complaint()));
        assertNotNull(complaintServiceImpl.getComplaintById(1L));
    }

    @Test
    void testUpdateComplaint() {
        Complaint existingComplaint = new Complaint();
        existingComplaint.setCustomerName("Apurva");
        existingComplaint.setDescription("Rooms are Not clean");

        Complaint updatedComplaint = new Complaint();
        updatedComplaint.setCustomerName("Apurva");
        updatedComplaint.setDescription("Rooms are Not clean and Food quality is not maintained");

        when(complaintRepository.findById(anyLong())).thenReturn(Optional.of(existingComplaint));
        when(complaintRepository.save(new Complaint())).thenReturn(updatedComplaint);

        assertDoesNotThrow(() -> complaintServiceImpl.updateComplaint(1L, updatedComplaint));
        verify(complaintRepository).save(existingComplaint);
    }

    @Test
    void testDeleteComplaint() {
        when(complaintRepository.findById(anyLong())).thenReturn(Optional.of(new Complaint()));
        assertDoesNotThrow(() -> complaintServiceImpl.deleteComplaint(1L));
        verify(complaintRepository).deleteById(1L);
    }
}

