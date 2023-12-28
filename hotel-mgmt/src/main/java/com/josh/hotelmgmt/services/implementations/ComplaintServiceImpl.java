package com.josh.hotelmgmt.services.implementations;

import com.josh.hotelmgmt.entities.Complaint;
import com.josh.hotelmgmt.repositories.ComplaintRepository;
import com.josh.hotelmgmt.services.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;
    @Override
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    @Override
    public void submitComplaint(Complaint complaint) {
        complaintRepository.save(complaint);
    }

    @Override
    public Complaint getComplaintById(Long complaintId) {
        return complaintRepository.findById(complaintId).orElse(null);
    }

    @Override
    public void updateComplaint(Long complaintId, Complaint updatedComplaint) {
        Complaint existingComplaint = getComplaintById(complaintId);
        if (existingComplaint != null) {
            existingComplaint.setCustomerName(updatedComplaint.getCustomerName());
            existingComplaint.setDescription(updatedComplaint.getDescription());
            complaintRepository.save(existingComplaint);
        }
    }

    @Override
    public void deleteComplaint(Long complaintId) {
        complaintRepository.deleteById(complaintId);
    }
}
