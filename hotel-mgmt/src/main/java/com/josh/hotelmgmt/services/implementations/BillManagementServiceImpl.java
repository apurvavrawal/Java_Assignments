package com.josh.hotelmgmt.services.implementations;

import com.josh.hotelmgmt.dto.BillManagementRequest;
import com.josh.hotelmgmt.entities.BillManagement;
import com.josh.hotelmgmt.entities.Order;
import com.josh.hotelmgmt.entities.Room;
import com.josh.hotelmgmt.entities.RoomBooking;
import com.josh.hotelmgmt.repositories.BillManagementRepository;
import com.josh.hotelmgmt.repositories.OrderRepository;
import com.josh.hotelmgmt.repositories.RoomBookingRepository;
import com.josh.hotelmgmt.repositories.RoomRepository;
import com.josh.hotelmgmt.services.BillManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
public class BillManagementServiceImpl implements BillManagementService {

    @Autowired
    private BillManagementRepository billManagementRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomBookingRepository roomBookingRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<BillManagement> getAllBills() {
        return billManagementRepository.findAll();
    }

    @Override
    public BillManagement getBillDetailsById(Long billId) {
        return billManagementRepository.findById(billId).orElse(null);
    }

    @Override
    public BillManagement createBill(BillManagementRequest bill) {

        BillManagement billManagement = new BillManagement();
        // Calculate total cost from RoomBooking and FoodOrder
        Double roomBookingTotalCost= 0.0d;
        Double foodOrderTotalCost= 0.0d;
        RoomBooking roomBooking = roomBookingRepository.findById(bill.getRoomBookingId()).orElse(null);
        if(roomBooking != null)
             roomBookingTotalCost = calculateRoomBookingTotalCost(roomBooking);
        Order order = orderRepository.findById(bill.getOrderId()).orElse(null);
        if(order != null)
             foodOrderTotalCost = calculateFoodOrderTotalCost(order);

        billManagement.setRoomBooking(roomBookingRepository.findById(bill.getRoomBookingId()).orElse(null));
        billManagement.setOrder(orderRepository.findById(billManagement.getBillId()).orElse(null));
        billManagement.setTotalCost(roomBookingTotalCost + foodOrderTotalCost);
        billManagement.setDate(LocalDate.now());
        billManagement.setPaymentMode(bill.getPaymentMode());

        billManagement.setPaymentStatus("Success");
        return billManagementRepository.save(billManagement);
    }

    @Override
    public void deleteBill(Long billId) {
        billManagementRepository.deleteById(billId);
    }

    private Double calculateRoomBookingTotalCost(RoomBooking roomBooking) {
        Room room = roomRepository.findById(roomBooking.getRoom().getRoomId()).orElse(null);
        long daysBetween = Duration.between(roomBooking.getBookingStartTime(), roomBooking.getBookingEndTime()).toDays();
        assert room != null;
        return room.getPricePerDay() * daysBetween;
    }

    private Double calculateFoodOrderTotalCost(Order order){
        return order.getOrderTotal();
    }
}
