package com.josh.hotelmgmt.repositories;

import com.josh.hotelmgmt.entities.BillManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillManagementRepository extends JpaRepository<BillManagement, Long> {
}
