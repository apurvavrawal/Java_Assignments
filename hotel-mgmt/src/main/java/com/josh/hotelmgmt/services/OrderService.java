package com.josh.hotelmgmt.services;

import com.josh.hotelmgmt.customExceptions.OrderNotFoundException;
import com.josh.hotelmgmt.entities.FoodOrder;
import com.josh.hotelmgmt.entities.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    Order getOrderByOrderId(long orderId);

    void createTotalFoodOrder(Order order);

    List<FoodOrder> getListOfDetailedFoodOrderByOrderId(Long orderId);

    void deleteTotalOrder(Long orderId) throws OrderNotFoundException;
}
