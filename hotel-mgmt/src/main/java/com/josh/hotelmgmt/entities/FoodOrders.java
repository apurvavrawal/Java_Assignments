package com.josh.hotelmgmt.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "food_orders")
public class FoodOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long token;

    @OneToOne
    @JoinColumn(name = "item_id")
    private FoodItem foodItemId;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order orderId;

    private int quantity;
    private Double total_price;
}
