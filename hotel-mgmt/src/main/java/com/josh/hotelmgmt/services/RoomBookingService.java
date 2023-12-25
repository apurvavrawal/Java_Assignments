package com.josh.hotelmgmt.services;

import com.josh.hotelmgmt.customExceptions.RoomNotAvailableException;
import com.josh.hotelmgmt.customExceptions.RoomNotFoundException;

import com.josh.hotelmgmt.entities.Room;
import com.josh.hotelmgmt.entities.RoomBooking;

import java.util.List;

public interface RoomBookingService {
    List<RoomBooking> getAllRoomBookings();

    RoomBooking getRoomByBookingId(Long bookingId);

    void bookRoom(RoomBooking roomBooking) throws RoomNotAvailableException;

    void deleteRoomBooking(Long bookingId) throws RoomNotFoundException;

   // boolean isRoomAvailable(Long bookingId, LocalDateTime startDate, LocalDateTime endDate);
    boolean isRoomAvailable(Long roomId);

}
