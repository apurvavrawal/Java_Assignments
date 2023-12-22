package com.josh.hotelmgmt.services;

import com.josh.hotelmgmt.customExceptions.RoomNotAvailableException;
import com.josh.hotelmgmt.customExceptions.RoomNotFoundException;

import com.josh.hotelmgmt.entities.RoomBooking;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomService {
    void bookRoom(RoomBooking roomBooking) throws RoomNotAvailableException;

    List<RoomBooking> getAllRooms();

    RoomBooking getRoomByBookingId(Long bookingId);

    void deleteRoomBooking(Long bookingId) throws RoomNotFoundException;

   // boolean isRoomAvailable(Long bookingId, LocalDateTime startDate, LocalDateTime endDate);
    boolean isRoomAvailable(Long roomId);

}
