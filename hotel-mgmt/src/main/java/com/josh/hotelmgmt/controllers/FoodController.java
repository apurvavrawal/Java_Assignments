package com.josh.hotelmgmt.controllers;

import com.josh.hotelmgmt.customExceptions.FoodNotAvailableException;
import com.josh.hotelmgmt.customExceptions.FoodOrderNotFoundException;
import com.josh.hotelmgmt.entities.FoodItem;
import com.josh.hotelmgmt.entities.FoodOrders;
import com.josh.hotelmgmt.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    FoodService foodService;

    //Returns all food items list
    @GetMapping("/")
    public List<FoodItem> getAllFoodItems(){
        return foodService.getAllFoodItems();
    }

    // Returns food item by item id provided
    @GetMapping("/{id}")
    public FoodItem getFoodItemById(long itemId){
        return foodService.getFoodItemById(itemId);
    }

    // Provides Food Order details based on token
    @GetMapping("/orders/{token}")
    public ResponseEntity<FoodOrders> getFoodOrderByToken(@PathVariable Long token){
        FoodOrders foodOrders = foodService.getFoodOrderById(token);
        if(foodOrders != null) {
            return new ResponseEntity<>(foodOrders, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Creates new food order
    @PostMapping("/orders")
    public ResponseEntity<String> createFoodOrder(@RequestBody FoodOrders foodOrders ){
        try {
            foodService.placeFoodOrder(foodOrders);
            return new ResponseEntity<>("Food Ordered successfully", HttpStatus.CREATED);
        } catch (FoodNotAvailableException e) {
            return new ResponseEntity<>("Requested Food is not available for placing Order", HttpStatus.BAD_REQUEST);
        }
    }

    // Deletes food order based on token
    @DeleteMapping("/orders/{token}")
    public ResponseEntity<String> deleteFoodOrder(@PathVariable Long token) {
        try {
            foodService.deletefoodOrder(token);
            return new ResponseEntity<>("Food Order is deleted successfully", HttpStatus.OK);
        } catch (FoodOrderNotFoundException e) {
            return new ResponseEntity<>("Order is not found for provided token", HttpStatus.NOT_FOUND);
        }
    }

    // checks food availability for provided token
    @GetMapping("/availability/{token}")
    public ResponseEntity<String> checkFoodAvailability(@PathVariable Long token) {
        if (foodService.isFoodAvailableForToken(token)) {
            return new ResponseEntity<>("Food is available for the provided token", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Food is not available for the provided token", HttpStatus.NOT_FOUND);
        }
    }

}
