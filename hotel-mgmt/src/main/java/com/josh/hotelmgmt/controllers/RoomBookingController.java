package com.josh.hotelmgmt.controllers;

import com.josh.hotelmgmt.customExceptions.RoomNotAvailableException;
import com.josh.hotelmgmt.customExceptions.RoomNotFoundException;
import com.josh.hotelmgmt.entities.RoomBooking;
import com.josh.hotelmgmt.services.RoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/roomBookings")
public class RoomBookingController {

    @Autowired
    private RoomBookingService roomBookingService;

    // Provides details of all Room Bookings
    @GetMapping("/")
    public List<RoomBooking> getAllRoomBookings(){
        return roomBookingService.getAllRoomBookings();
    }

    // Provides Room details based on Room Number
    @GetMapping("/{bookingId}")
    public ResponseEntity<RoomBooking> getRoomByBookingId(@PathVariable Long bookingId){
        RoomBooking roomBooking = roomBookingService.getRoomByBookingId(bookingId);
        if(roomBooking != null) {
            return new ResponseEntity<>(roomBooking,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Creates request for Room booking
    @PostMapping("/")
    public ResponseEntity<String> bookRoom(@RequestBody RoomBooking roomBooking) {
        try {
            roomBookingService.bookRoom(roomBooking);
            return new ResponseEntity<>("Room booked successfully", HttpStatus.CREATED);
        } catch (RoomNotAvailableException e) {
            return new ResponseEntity<>("Room is not available for booking", HttpStatus.BAD_REQUEST);
        }
    }

    // Deletes booking for a Room based on booking Id
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> deleteRoomBooking(@PathVariable Long bookingId) {
        try {
            roomBookingService.deleteRoomBooking(bookingId);
            return new ResponseEntity<>("Room booking deleted successfully", HttpStatus.OK);
        } catch (RoomNotFoundException e) {
            return new ResponseEntity<>("Room not found", HttpStatus.NOT_FOUND);
        }
    }

    // Checks Availability for Room based on date
    @GetMapping("/availability/{roomId}")
    /*public ResponseEntity<String> checkRoomAvailability(
            @RequestParam Long bookingId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime endDate) {
        if (roomService.isRoomAvailable(bookingId, startDate, endDate)) {
            return new ResponseEntity<>("Room is available for booking", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Room is not available for the mentioned date range", HttpStatus.BAD_REQUEST);
        }
    }*/

    public ResponseEntity<String> checkRoomAvailability(@RequestParam Long roomId){
        if(roomBookingService.isRoomAvailable(roomId)){
            return new ResponseEntity<>("Room is available for booking", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Room is not available for booking", HttpStatus.BAD_REQUEST);
        }
    }
}

