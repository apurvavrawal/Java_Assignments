package com.josh.hotelmgmt.repositories;

import com.josh.hotelmgmt.entities.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint , Long> {
}
