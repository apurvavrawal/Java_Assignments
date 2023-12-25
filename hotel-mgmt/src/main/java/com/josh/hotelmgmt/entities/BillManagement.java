package com.josh.hotelmgmt.entities;

import com.josh.hotelmgmt.DTO.PaymentMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bill_managements")
public class BillManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long billId;

    @ManyToOne
    private RoomBooking roomBooking;

    @OneToOne
    private Order order;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    private Double totalCost;

    private String paymentStatus;

    private LocalDate date;

}
