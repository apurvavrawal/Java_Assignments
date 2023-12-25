package com.josh.hotelmgmt.controllers;

import com.josh.hotelmgmt.customExceptions.OrderNotFoundException;
import com.josh.hotelmgmt.entities.FoodOrder;
import com.josh.hotelmgmt.entities.Order;
import com.josh.hotelmgmt.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    // Returns list of all Orders
    @GetMapping("/")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    // Returns Order by Order Id provided
    @GetMapping("/{orderId}")
    public Order getOrderByOrderId(long orderId){
        return orderService.getOrderByOrderId(orderId);
    }

    // Returns complete details of food order of which OrderID provided
    @GetMapping("/details/{orderId}")
    public ResponseEntity<List<FoodOrder>> getListOfDetailedFoodOrderByOrderId(@PathVariable Long orderId){
        List<FoodOrder> foodOrder = orderService.getListOfDetailedFoodOrderByOrderId(orderId);
        if(foodOrder != null) {
            return new ResponseEntity<>(foodOrder, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Creates new Order
    @PostMapping("/")
    public ResponseEntity<String> createTotalFoodOrder(@RequestBody Order order){
            orderService.createTotalFoodOrder(order);
            return new ResponseEntity<>("Total Food Order Placed successfully", HttpStatus.CREATED);
    }

    // Deletes the Order
    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteTotalOrder(@PathVariable Long orderId) {
        try{
            orderService.deleteTotalOrder(orderId);
            return new ResponseEntity<>("Total Order is deleted successfully", HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>("Order is not found for provided Order Id", HttpStatus.NOT_FOUND);
        }

    }
}