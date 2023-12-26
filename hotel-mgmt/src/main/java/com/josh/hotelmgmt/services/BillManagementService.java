package com.josh.hotelmgmt.services;

import com.josh.hotelmgmt.dto.BillManagementRequest;
import com.josh.hotelmgmt.entities.BillManagement;

import java.util.List;

public interface BillManagementService {
    List<BillManagement> getAllBills();

    BillManagement getBillDetailsById(Long billId);

    BillManagement createBill(BillManagementRequest bill);
    void deleteBill(Long billId);
}
