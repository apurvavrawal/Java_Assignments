package com.josh.hotelmgmt.services;

import com.josh.hotelmgmt.entities.Complaint;

import java.util.List;

public interface ComplaintService {
    List<Complaint> getAllComplaints();

    void submitComplaint(Complaint complaint);

    Complaint getComplaintById(Long complaintId);

    void updateComplaint(Long complaintId, Complaint updatedComplaint);

    void deleteComplaint(Long complaintId);
}
