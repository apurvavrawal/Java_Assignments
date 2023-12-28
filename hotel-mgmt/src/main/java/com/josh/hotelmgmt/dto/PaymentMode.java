package com.josh.hotelmgmt.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public enum PaymentMode {
    DEBIT_CARD("Debit Card"),
    CASH("Cash"),
    UPI("UPI");

    private final String mode;

    PaymentMode(String mode) {
        this.mode = mode;
    }

}
