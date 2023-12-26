package com.josh.hotelmgmt.controllers;

import com.josh.hotelmgmt.customExceptions.FoodNotAvailableException;
import com.josh.hotelmgmt.entities.FoodItem;
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
    private FoodService foodService;

    //Returns all food items list
    @GetMapping("/")
    public List<FoodItem> getAllFoodItems(){
        return foodService.getAllFoodItems();
    }

    // Returns food item by item id provided
    @GetMapping("/{foodItemId}")
    public FoodItem getFoodItemById(@PathVariable long foodItemId){
        return foodService.getFoodItemById(foodItemId);
    }

    // Create new Food Item Entry
    @PostMapping("/")
    public ResponseEntity<String> createNewFoodEntry(@RequestBody FoodItem foodItem){
        foodService.createNewFoodEntry(foodItem);
        return new ResponseEntity<>("Food Entry is added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{foodItemId}")
    public void deleteFoodItem(@PathVariable long foodItemId) throws FoodNotAvailableException {
        foodService.deleteFoodItem(foodItemId);
    }


}
