package com.josh.hotelmgmt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BillManagementRequest {

    private long roomBookingId;
    private long orderId;
    private PaymentMode paymentMode;

}
