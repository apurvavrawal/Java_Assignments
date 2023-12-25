package com.josh.hotelmgmt.controllers;

import com.josh.hotelmgmt.entities.BillManagement;
import com.josh.hotelmgmt.services.BillManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillManagementController {

    @Autowired
    private BillManagementService billManagementService;

    @GetMapping("/")
    public ResponseEntity<List<BillManagement>> getAllBills(){
        List<BillManagement> bills = billManagementService.getAllBills();
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @GetMapping("/{billId}")
    public ResponseEntity<BillManagement> getBillDetailsById(@PathVariable Long billId) {
        BillManagement bill = billManagementService.getBillDetailsById(billId);
        if (bill != null) {
            return new ResponseEntity<>(bill, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<BillManagement> createBill(@RequestBody BillManagement bill) {
        BillManagement createdBill = billManagementService.createBill(bill);
        return new ResponseEntity<>(createdBill, HttpStatus.CREATED);
    }

    @PutMapping("/{billId}")
    public ResponseEntity<BillManagement> updateBill(@PathVariable Long billId, @RequestBody BillManagement bill) {
        BillManagement updatedBill = billManagementService.updateBill(billId, bill);
        if (updatedBill != null) {
            return new ResponseEntity<>(updatedBill, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{billId}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long billId) {
        billManagementService.deleteBill(billId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
