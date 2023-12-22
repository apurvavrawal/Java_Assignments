package com.josh.hotelmgmt.services;

import com.josh.hotelmgmt.customExceptions.FoodNotAvailableException;
import com.josh.hotelmgmt.customExceptions.FoodOrderNotFoundException;
import com.josh.hotelmgmt.entities.FoodItem;
import com.josh.hotelmgmt.entities.FoodOrders;

import java.util.List;

public interface FoodService {
    List<FoodItem> getAllFoodItems();

    FoodItem getFoodItemById(Long itemId);

    FoodOrders getFoodOrderById(Long token);

    void placeFoodOrder(FoodOrders foodOrders) throws FoodNotAvailableException;

    void deletefoodOrder(Long token) throws FoodOrderNotFoundException;

    boolean isFoodAvailableForToken(Long token);
}
