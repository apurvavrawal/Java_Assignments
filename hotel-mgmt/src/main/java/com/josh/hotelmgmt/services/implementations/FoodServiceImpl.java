package com.josh.hotelmgmt.services.implementations;

import com.josh.hotelmgmt.customExceptions.FoodNotAvailableException;
import com.josh.hotelmgmt.entities.FoodItem;
import com.josh.hotelmgmt.repositories.FoodItemsRepository;
import com.josh.hotelmgmt.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodItemsRepository foodItemsRepository;
    @Override
    public List<FoodItem> getAllFoodItems() {
        return foodItemsRepository.findAll();
    }

    @Override
    public FoodItem getFoodItemById(long foodItemId) {
        return foodItemsRepository.findById(foodItemId).orElse(null);
    }

    @Override
    public void createNewFoodEntry(FoodItem foodItem) {
        FoodItem food = new FoodItem();
        food.setFoodItemName(foodItem.getFoodItemName());
        food.setFoodItemPrice(foodItem.getFoodItemPrice());
        foodItemsRepository.save(food);
    }

    @Override
    public void deleteFoodItem(long foodItemId) throws FoodNotAvailableException {
        FoodItem foodItem = foodItemsRepository.findById(foodItemId).orElseThrow(()-> new FoodNotAvailableException("Food Item with Id: "+ foodItemId + " is not available"));
        foodItemsRepository.delete(foodItem);
    }

}
