package com.josh.hotelmgmt.services;

import com.josh.hotelmgmt.dto.BillManagementRequest;
import com.josh.hotelmgmt.dto.PaymentMode;
import com.josh.hotelmgmt.entities.BillManagement;
import com.josh.hotelmgmt.entities.Order;
import com.josh.hotelmgmt.entities.Room;
import com.josh.hotelmgmt.entities.RoomBooking;
import com.josh.hotelmgmt.repositories.BillManagementRepository;
import com.josh.hotelmgmt.repositories.OrderRepository;
import com.josh.hotelmgmt.repositories.RoomBookingRepository;
import com.josh.hotelmgmt.repositories.RoomRepository;
import com.josh.hotelmgmt.services.implementations.BillManagementServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BillManagementServiceImplTest {
    @Mock
    private BillManagementRepository billManagementRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private RoomBookingRepository roomBookingRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private BillManagementServiceImpl billManagementServiceImpl;

    @Test
    void testGetAllBills() {
        when(billManagementRepository.findAll()).thenReturn(List.of(new BillManagement(), new BillManagement()));
        assertEquals(2, billManagementServiceImpl.getAllBills().size());
    }

    @Test
    void testGetBillDetailsById() {
        when(billManagementRepository.findById(anyLong())).thenReturn(Optional.of(new BillManagement()));
        assertNotNull(billManagementServiceImpl.getBillDetailsById(1L));
    }

    @Test
    void testCreateBill() {

        BillManagementRequest billRequest = new BillManagementRequest();
        billRequest.setRoomBookingId(1L);
        billRequest.setOrderId(2L);
        billRequest.setPaymentMode(PaymentMode.CASH);

        when(roomBookingRepository.findById(anyLong())).thenReturn(Optional.of(createSampleRoomBooking()));
        when(roomRepository.findById(anyLong())).thenReturn(Optional.of(createSampleRoom()));
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(createSampleOrder()));
        when(billManagementRepository.save(new BillManagement())).thenReturn(createSampleBill());

        assertDoesNotThrow(() -> billManagementServiceImpl.createBill(billRequest));
    }

    @Test
    void testDeleteBill() {
        when(billManagementRepository.findById(anyLong())).thenReturn(Optional.of(new BillManagement()));
        assertDoesNotThrow(() -> billManagementServiceImpl.deleteBill(1L));
    }

    private Room createSampleRoom(){
        Room room = new Room();
        room.setRoomId(1L);
        room.setPricePerDay(1000.0);
        return room;
    }
    private RoomBooking createSampleRoomBooking() {

        RoomBooking roomBooking = new RoomBooking();
        roomBooking.setRoom(createSampleRoom());
        roomBooking.setBookingStartTime(LocalDateTime.now());
        roomBooking.setBookingEndTime(LocalDateTime.now().plusDays(3));

        return roomBooking;
    }

    private Order createSampleOrder() {
        Order order = new Order();
        order.setOrderId(2L);
        order.setOrderTotal(250.0);

        return order;
    }

    private BillManagement createSampleBill() {
        BillManagement billManagement = new BillManagement();
        billManagement.setBillId(1L);
        billManagement.setRoomBooking(createSampleRoomBooking());
        billManagement.setOrder(createSampleOrder());
        billManagement.setTotalCost(250.0);
        billManagement.setDate(LocalDate.now());
        billManagement.setPaymentMode(PaymentMode.CASH);
        billManagement.setPaymentStatus("Success");

        return billManagement;
    }
}

