package com.josh.hotelmgmt.services;

import com.josh.hotelmgmt.entities.BillManagement;

import java.util.List;

public interface BillManagementService {
    List<BillManagement> getAllBills();

    BillManagement getBillDetailsById(Long billId);

    BillManagement createBill(BillManagement bill);

    BillManagement updateBill(Long billId, BillManagement bill);

    void deleteBill(Long billId);
}
