package com.josh.hotelmgmt.services.implementations;

import com.josh.hotelmgmt.customExceptions.OrderNotFoundException;

import com.josh.hotelmgmt.entities.FoodOrder;
import com.josh.hotelmgmt.entities.Order;
import com.josh.hotelmgmt.repositories.FoodOrdersRepository;
import com.josh.hotelmgmt.repositories.OrderRepository;
import com.josh.hotelmgmt.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public long createOrder() {
        Order order = new Order();
        order.setOrderTotal(0);
        orderRepository.save(order);
        return order.getOrderId();
    }

    @Override
    public List<FoodOrder> getListOfDetailedFoodOrderByOrderId(Long orderId) {
        return foodOrdersRepository.findAllById(Collections.singleton(orderId));
    }

    @Override
    public void deleteTotalOrder(Long orderId) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order with order Id: "+ orderId + "not found"));
        orderRepository.delete(order);
    }

    @Override
    public void createTotalOrder(long orderId) {
        //create new order
        Order newOrder = orderRepository.findById(orderId).orElse(null);
        double orderAmount = 0;
        List<FoodOrder> foodOrders = foodOrdersRepository.findById(orderId).stream().toList();
        Iterator<FoodOrder> foodOrderIterator = foodOrders.iterator();
        while (foodOrderIterator.hasNext()){
            orderAmount = orderAmount + foodOrderIterator.next().getTotalPrice();
        }
        orderAmount = foodOrders.stream().mapToDouble(FoodOrder::getTotalPrice).sum();
        assert newOrder != null;
        newOrder.setOrderTotal(orderAmount);
        orderRepository.save(newOrder);
    }
}
