package com.josh.hotelmgmt.controllers;

import com.josh.hotelmgmt.customExceptions.FoodNotAvailableException;
import com.josh.hotelmgmt.customExceptions.FoodOrderNotFoundException;
import com.josh.hotelmgmt.dto.FoodOrderRequest;
import com.josh.hotelmgmt.entities.FoodOrder;
import com.josh.hotelmgmt.services.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foodorders")
public class FoodOrderController {
    @Autowired
    private FoodOrderService foodOrderService;

    // Provides List of All Food Orders
    @GetMapping("/")
    public List<FoodOrder> getAllFoodOrders()
    {
        return foodOrderService.getAllFoodOrders();
    }

    // Provides Food Order details based on foodItemId
    @GetMapping("/{foodOrderId}")
    public ResponseEntity<FoodOrder> getFoodOrderByFoodOrderId(@PathVariable Long foodOrderId){
        FoodOrder foodOrder = foodOrderService.getFoodOrderById(foodOrderId);
        if(foodOrder != null) {
            return new ResponseEntity<>(foodOrder, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Creates new food order
    @PostMapping("/")
    public ResponseEntity<String> createFoodOrder(@RequestBody FoodOrderRequest foodOrderRequest ){
        try {
            foodOrderService.placeFoodOrder(foodOrderRequest);
            return new ResponseEntity<>("Food Ordered successfully", HttpStatus.CREATED);
        } catch (FoodNotAvailableException e) {
            return new ResponseEntity<>("Requested Food is not available for placing Order", HttpStatus.BAD_REQUEST);
        }
    }

    // Deletes food order based on Food OrderID
    @DeleteMapping("/{foodOrderId}")
    public ResponseEntity<String> deleteFoodOrder(@PathVariable Long foodOrderId) {
        try {
            foodOrderService.deleteFoodOrder(foodOrderId);
            return new ResponseEntity<>("Food Order is deleted successfully", HttpStatus.OK);
        } catch (FoodOrderNotFoundException e) {
            return new ResponseEntity<>("Order is not found for provided Food Order Id", HttpStatus.NOT_FOUND);
        }
    }
    // checks food availability for provided Food Order
    @GetMapping("/availability/{foodItemId}")
    public ResponseEntity<String> checkFoodAvailability(@PathVariable Long foodItemId) {
        if (foodOrderService.isFoodAvailableForOrder(foodItemId)) {
            return new ResponseEntity<>("Food is available for the provided Food Item Id", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Food is not available for the provided Food Item Id", HttpStatus.NOT_FOUND);
        }
    }

}
