package com.josh.hotelmgmt.services.implementations;

import com.josh.hotelmgmt.entities.BillManagement;
import com.josh.hotelmgmt.entities.Order;
import com.josh.hotelmgmt.entities.Room;
import com.josh.hotelmgmt.entities.RoomBooking;
import com.josh.hotelmgmt.DTO.PaymentMode;
import com.josh.hotelmgmt.repositories.BillManagementRepository;
import com.josh.hotelmgmt.repositories.RoomRepository;
import com.josh.hotelmgmt.services.BillManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BillManagementServiceImpl implements BillManagementService {

    @Autowired
    private BillManagementRepository billManagementRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<BillManagement> getAllBills() {
        return billManagementRepository.findAll();
    }

    @Override
    public BillManagement getBillDetailsById(Long billId) {
        return billManagementRepository.findById(billId).orElse(null);
    }

    @Override
    public BillManagement createBill(BillManagement bill) {
        // Calculate total cost from RoomBooking and FoodOrder
        Double roomBookingTotalCost = calculateRoomBookingTotalCost(bill.getRoomBooking());
        Double foodOrderTotalCost = calculateFoodOrderTotalCost(bill.getOrder());

        bill.setTotalCost(roomBookingTotalCost + foodOrderTotalCost);
        bill.setDate(LocalDate.now());

        String payment = JOptionPane.showInputDialog ("Please enter Payment Mode type (Debit Card, Cash ,UPI)");
        PaymentMode paymentMode = PaymentMode.valueOf(payment);
        JOptionPane.showMessageDialog (null, payment);
        bill.setPaymentMode(paymentMode);

        bill.setPaymentStatus("Success");
        return billManagementRepository.save(bill);
    }
    @Override
    public BillManagement updateBill(Long billId, BillManagement bill) {
        BillManagement existingBill = billManagementRepository.findById(billId).orElse(null);
        if (existingBill != null) {
            // Calculate total cost from RoomBooking and FoodOrder
            Double roomBookingTotalCost = calculateRoomBookingTotalCost(bill.getRoomBooking());
            Double foodOrderTotalCost = calculateFoodOrderTotalCost(bill.getOrder());

            // Set total cost in the updated Bill entity
            bill.setTotalCost(roomBookingTotalCost + foodOrderTotalCost);
            bill.setPaymentMode(PaymentMode.DEBIT_CARD);

            return billManagementRepository.save(bill);
        } else {
            return null;
        }
    }

    @Override
    public void deleteBill(Long billId) {
        billManagementRepository.deleteById(billId);
    }

    private Double calculateRoomBookingTotalCost(RoomBooking roomBooking) {
        RoomBooking roomBook =  new RoomBooking();
        Optional<Room> room = roomRepository.findById(roomBooking.getRoomId().getRoomId());
        long daysBetween = Duration.between(roomBook.getBookingStartTime(), roomBooking.getBookingEndTime()).toDays();
        return room.get().getPricePerDay() * daysBetween;
    }

    private Double calculateFoodOrderTotalCost(Order order){
        return order.getOrderTotal();
    }
}
