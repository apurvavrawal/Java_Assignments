package com.josh.hotelmgmt.controllers;

import com.josh.hotelmgmt.entities.Complaint;
import com.josh.hotelmgmt.services.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {
    @Autowired
    private ComplaintService complaintService;

    // Returns all complaint's details
    @GetMapping("/")
     public List<Complaint> getAllComplaints(){
        return complaintService.getAllComplaints();
    }

    // creates new complaint and saves into DB
    @PostMapping("/")
    public ResponseEntity<String> submitComplaint(@RequestBody Complaint complaint) {
        complaintService.submitComplaint(complaint);
        return new ResponseEntity<>("Complaint submitted successfully", HttpStatus.CREATED);
    }

    // Returns complaint by complaintId provided
    @GetMapping("/{complaintId}")
    public ResponseEntity<Complaint> getComplaintById(@PathVariable Long complaintId) {
        Complaint complaint = complaintService.getComplaintById(complaintId);
        if (complaint != null) {
            return new ResponseEntity<>(complaint, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Updates the complaint (Customer name and description is allowed to modify)
    @PutMapping("/{complaintId}")
    public ResponseEntity<String> updateComplaint(@PathVariable Long complaintId, @RequestBody Complaint updatedComplaint) {
        complaintService.updateComplaint(complaintId, updatedComplaint);
        return new ResponseEntity<>("Complaint updated successfully", HttpStatus.OK);
    }

    // Delete a Complaint with complaint Id provided
    @DeleteMapping("/{complaintId}")
    public ResponseEntity<String> deleteComplaint(@PathVariable Long complaintId) {
        complaintService.deleteComplaint(complaintId);
        return new ResponseEntity<>("Complaint deleted successfully", HttpStatus.OK);
    }
}
