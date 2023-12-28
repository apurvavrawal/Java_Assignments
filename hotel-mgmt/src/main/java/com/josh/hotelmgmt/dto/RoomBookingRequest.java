package com.josh.hotelmgmt.dto;

import com.josh.hotelmgmt.entities.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoomBookingRequest {
    private long roomId;

    private long customerId;
    private LocalDateTime bookingStartTime;
    private LocalDateTime bookingEndTime;
}
