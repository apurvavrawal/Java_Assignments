package com.josh.hotelmgmt.services.implementations;

import com.josh.hotelmgmt.customExceptions.FoodNotAvailableException;
import com.josh.hotelmgmt.customExceptions.FoodOrderNotFoundException;
import com.josh.hotelmgmt.entities.FoodItem;
import com.josh.hotelmgmt.entities.FoodOrders;
import com.josh.hotelmgmt.repositories.FoodItemsRepository;
import com.josh.hotelmgmt.repositories.FoodOrdersRepository;
import com.josh.hotelmgmt.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    FoodItemsRepository foodItemsRepository;

    @Autowired
    FoodOrdersRepository foodOrdersRepository;
    @Override
    public List<FoodItem> getAllFoodItems() {
        return foodItemsRepository.findAll();
    }

    @Override
    public FoodItem getFoodItemById(Long itemId) {
        return foodItemsRepository.findById(itemId).orElse(null);
    }
    @Override
    public FoodOrders getFoodOrderById(Long token) {
        return foodOrdersRepository.findById(token).orElse(null);
    }

    @Override
    public void placeFoodOrder(FoodOrders foodOrders) throws FoodNotAvailableException {
        if(!isFoodAvailableForToken(foodOrders.getToken())){
            throw new FoodNotAvailableException("Food is not available for placing Order");
        }
        //create new food order
        FoodOrders orders = new FoodOrders();
//        orders.setFoodItem(foodOrders.getFoodItem());
        Optional<FoodItem> foodItem = foodItemsRepository.findById(foodOrders.getFoodItemId().getItemId());
        orders.setQuantity(foodOrders.getQuantity());
        orders.setTotal_price(foodItem.get().getItemPrice() * foodOrders.getQuantity());
        foodOrdersRepository.save(orders);
    }

    @Override
    public void deletefoodOrder(Long token) throws FoodOrderNotFoundException{
        FoodOrders foodOrders = foodOrdersRepository.findById(token).orElseThrow(() -> new FoodOrderNotFoundException("Food Order with token"+ token + "not available"));
        foodOrdersRepository.delete(foodOrders);
    }

    @Override
    public boolean isFoodAvailableForToken(Long token) {
        List<FoodOrders> foodOrders = foodOrdersRepository.findAllById(Collections.singleton(token));
        // Return true if at least one order is found for token otherwise false
        return !foodOrders.isEmpty();
    }
}
