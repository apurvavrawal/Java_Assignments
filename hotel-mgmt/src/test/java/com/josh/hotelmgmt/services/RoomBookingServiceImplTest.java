package com.josh.hotelmgmt.services;

import com.josh.hotelmgmt.dto.RoomBookingRequest;
import com.josh.hotelmgmt.entities.Room;
import com.josh.hotelmgmt.entities.RoomBooking;
import com.josh.hotelmgmt.repositories.RoomBookingRepository;
import com.josh.hotelmgmt.repositories.RoomRepository;
import com.josh.hotelmgmt.services.implementations.RoomBookingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RoomBookingServiceImplTest {
    @InjectMocks
    private RoomBookingServiceImpl roomBookingServiceImpl;

    @Mock
    private RoomBookingRepository roomBookingRepository;

    @Mock
    private RoomRepository roomRepository;

    @Test
    public void testGetAllRoomBookings() {
        when(roomBookingRepository.findAll()).thenReturn(List.of(new RoomBooking(), new RoomBooking()));
        assertEquals(2, roomBookingServiceImpl.getAllRoomBookings().size());
    }

    @Test
    public void testGetRoomByBookingId() {
        when(roomBookingRepository.findById(anyLong())).thenReturn(Optional.of(new RoomBooking()));
        assertNotNull(roomBookingServiceImpl.getRoomByBookingId(1L));
    }

    @Test
    public void testBookRoom() {

        when(roomBookingRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(roomRepository.findById(anyLong())).thenReturn(Optional.of(new Room()));

        RoomBookingRequest bookingRequest = new RoomBookingRequest();
        bookingRequest.setRoomId(1L);
        bookingRequest.setBookingStartTime(LocalDateTime.now());
        bookingRequest.setBookingEndTime(LocalDateTime.now().plusDays(2));

        assertDoesNotThrow(() -> roomBookingServiceImpl.bookRoom(bookingRequest));
    }

    /*@Test
    public void testBookRoomWhenRoomNotAvailable() {

        when(roomBookingRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(roomRepository.findById(anyLong())).thenReturn(Optional.of(new Room()));

        RoomBookingRequest bookingRequest = new RoomBookingRequest();
        bookingRequest.setRoomId(1L);
        bookingRequest.setBookingStartTime(LocalDateTime.now());
        bookingRequest.setBookingEndTime(LocalDateTime.now().plusDays(1));

        RoomNotAvailableException exception = assertThrows(RoomNotAvailableException.class,() -> roomBookingServiceImpl.bookRoom(bookingRequest));
        assertEquals("Room is not available for booking with Room Number: 1", exception.getMessage());
    }*/

    @Test
    public void testDeleteRoomBooking() {
        when(roomBookingRepository.findById(anyLong())).thenReturn(Optional.of(new RoomBooking()));
        assertDoesNotThrow(() -> roomBookingServiceImpl.deleteRoomBooking(1L));
    }

    @Test
    public void testIsRoomAvailable() {
        when(roomBookingRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertTrue(roomBookingServiceImpl.isRoomAvailable(1L));
    }
}
