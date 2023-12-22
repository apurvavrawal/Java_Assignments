package com.josh.hotelmgmt.repositories;

import com.josh.hotelmgmt.entities.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import java.util.List;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, Long> {
   /* @Query("SELECT COUNT(*) FROM room_booking b" +
            "JOIN room r ON b.id = r.id" +
            "WHERE b.id = booking_id" +
            "AND (booking_end_time < b.booking_start_time OR booking_start_time > b.booking_end_time)")
    long roomAvailability(@Param("booking_id") Long bookingId,
                          @Param("booking_start_time") LocalDateTime bookingStartTime,
                          @Param("booking_end_time") LocalDateTime bookingEndTime);*/
    //= findByRoomNumberAndStartDateBeforeAndEndDateAfter(Long bookingId, LocalDateTime startDate, LocalDateTime endDate);


    @Query("SELECT count(*) FROM room_booking b JOIN room r ON b.id = r.id where b.id = room_id")
    long roomAvailability(@Param("room_id") Long roomId);

}

