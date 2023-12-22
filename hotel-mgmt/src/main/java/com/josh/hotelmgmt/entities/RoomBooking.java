package com.josh.hotelmgmt.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room_bookings")
public class RoomBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room roomId;

    private long customerId;
    private LocalDateTime bookingStartTime;
    private LocalDateTime bookingEndTime;
    private boolean isBooked;
    private double totalPrice;
}
