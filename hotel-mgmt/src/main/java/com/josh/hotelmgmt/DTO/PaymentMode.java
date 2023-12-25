package com.josh.hotelmgmt.DTO;

public enum PaymentMode {
    DEBIT_CARD("Debit Card"),
    CASH("Cash"),
    UPI("UPI");

    private final String mode;

    PaymentMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
