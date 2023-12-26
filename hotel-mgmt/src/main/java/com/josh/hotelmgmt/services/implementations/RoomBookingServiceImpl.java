package com.josh.hotelmgmt.services.implementations;

import com.josh.hotelmgmt.customExceptions.RoomNotAvailableException;
import com.josh.hotelmgmt.customExceptions.RoomNotFoundException;
import com.josh.hotelmgmt.dto.RoomBookingRequest;
import com.josh.hotelmgmt.entities.Room;
import com.josh.hotelmgmt.entities.RoomBooking;
import com.josh.hotelmgmt.repositories.RoomBookingRepository;
import com.josh.hotelmgmt.repositories.RoomRepository;
import com.josh.hotelmgmt.services.RoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RoomBookingServiceImpl implements RoomBookingService {

    @Autowired
    private RoomBookingRepository roomBookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<RoomBooking> getAllRoomBookings() {
        return roomBookingRepository.findAll();
    }

    @Override
    public RoomBooking getRoomByBookingId(Long bookingId) {
        return roomBookingRepository.findById(bookingId).orElse(null);
    }

    @Override
    public void bookRoom(RoomBookingRequest roomBookingRequest) throws RoomNotAvailableException {

        // check if room is available for booking

        /*if(!isRoomAvailable(roomBooking.getBookingId(), roomBooking.getBookingStartTime(), roomBooking.getBookingEndTime())){
            throw new RoomNotAvailableException("Room is not available for booking");
        }*/
        if( ! isRoomAvailable(roomBookingRequest.getRoomId())){
            throw new RoomNotAvailableException("Room is not available for booking with Room Number: " + roomBookingRequest.getRoomId());
        }
        //save the new booking for Room

        RoomBooking roomBook =  new RoomBooking();
        Optional<Room> room = roomRepository.findById(roomBookingRequest.getRoomId());
        long daysBetween = Duration.between(roomBookingRequest.getBookingStartTime(), roomBookingRequest.getBookingEndTime()).toDays();
        roomBook.setRoom(room.get());
        roomBook.setCustomerId(roomBookingRequest.getCustomerId());
        roomBook.setBookingStartTime(LocalDateTime.now());
        roomBook.setBookingEndTime(LocalDateTime.now().plusDays(1));
        roomBook.setBooked(true);
        roomBook.setTotalPrice(room.get().getPricePerDay() * daysBetween);
        roomBookingRepository.save(roomBook);
    }

    @Override
    public void deleteRoomBooking(Long bookingId) throws RoomNotFoundException {
        RoomBooking roomBooking = roomBookingRepository.findById(bookingId).orElseThrow(() -> new RoomNotFoundException("Room not Found for Room Number: "+ bookingId ));
        roomBookingRepository.delete(roomBooking);
    }

   /* @Override
    public boolean isRoomAvailable(Long bookingId, LocalDateTime startDate, LocalDateTime endDate) {
        long bookings = roomBookingRepository.roomAvailability(bookingId, startDate, endDate);
        if(Objects.isNull(bookings))
            return false;
        return true;
    }*/

    @Override
    public boolean isRoomAvailable(long roomId){
        Optional<RoomBooking> roomBooking = roomBookingRepository.findById(roomId);

        return roomBooking.isEmpty() || !roomBooking.get().isBooked();
    }
}
