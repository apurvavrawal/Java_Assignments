package com.josh.hotelmgmt.services;

import com.josh.hotelmgmt.customExceptions.FoodNotAvailableException;
import com.josh.hotelmgmt.entities.FoodItem;


import java.util.List;

public interface FoodService {
    List<FoodItem> getAllFoodItems();
    FoodItem getFoodItemById(long foodItemId);
    void createNewFoodEntry(FoodItem foodItem);
    void deleteFoodItem(long foodItemId) throws FoodNotAvailableException;
}
