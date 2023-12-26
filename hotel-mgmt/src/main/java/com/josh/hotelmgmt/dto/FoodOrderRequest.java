package com.josh.hotelmgmt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FoodOrderRequest {
    private long foodItemId;
    private int quantity;
    private long orderId;
}
