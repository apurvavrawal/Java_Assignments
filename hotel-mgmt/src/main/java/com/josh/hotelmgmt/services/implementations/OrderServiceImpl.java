package com.josh.hotelmgmt.services.implementations;

import com.josh.hotelmgmt.customExceptions.OrderNotFoundException;

import com.josh.hotelmgmt.entities.FoodOrder;
import com.josh.hotelmgmt.entities.Order;
import com.josh.hotelmgmt.repositories.FoodOrdersRepository;
import com.josh.hotelmgmt.repositories.OrderRepository;
import com.josh.hotelmgmt.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FoodOrdersRepository foodOrdersRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderByOrderId(long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public void createTotalFoodOrder(Order order) {
        //create new order
        Order newOrder = new Order();
        double orderAmount = 0.0d;
        Optional<FoodOrder> foodOrder = foodOrdersRepository.findById(order.getOrderId());
        while(foodOrder.isEmpty()){
            orderAmount = orderAmount+ foodOrder.get().getTotalPrice();
        }
        newOrder.setOrderTotal(orderAmount);
        orderRepository.save(newOrder);
    }

    @Override
    public List<FoodOrder> getListOfDetailedFoodOrderByOrderId(Long orderId) {
        return foodOrdersRepository.findAllById(Collections.singleton(orderId));
    }

    @Override
    public void deleteTotalOrder(Long orderId) throws OrderNotFoundException {
        orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order with order Id: "+ orderId + "not found"));
    }
}
