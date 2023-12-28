package com.josh.hotelmgmt.services;

import com.josh.hotelmgmt.customExceptions.FoodNotAvailableException;
import com.josh.hotelmgmt.customExceptions.FoodOrderNotFoundException;
import com.josh.hotelmgmt.dto.FoodOrderRequest;
import com.josh.hotelmgmt.entities.FoodOrder;

import java.util.List;

public interface FoodOrderService {
    List<FoodOrder> getAllFoodOrders();

    FoodOrder getFoodOrderById(Long foodItemId);

    boolean isFoodAvailableForOrder(long foodItemId);

    void placeFoodOrder(FoodOrderRequest foodOrderRequest) throws FoodNotAvailableException;

    void deleteFoodOrder(Long foodOrderId) throws FoodOrderNotFoundException;

//    void updateFoodOrder(Long foodOrderId, FoodOrder updatedFoodOrder);
}
