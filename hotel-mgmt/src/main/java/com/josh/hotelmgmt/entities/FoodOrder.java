package com.josh.hotelmgmt.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "food_orders")
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long foodOrderId;

    @ManyToOne
    @JoinColumn(name = "food_item_id")
    private FoodItem foodItemId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orderId;

    private int quantity;
    private double totalPrice;
}
