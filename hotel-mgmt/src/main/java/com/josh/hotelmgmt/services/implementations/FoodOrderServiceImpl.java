package com.josh.hotelmgmt.services.implementations;

import com.josh.hotelmgmt.customExceptions.FoodNotAvailableException;
import com.josh.hotelmgmt.customExceptions.FoodOrderNotFoundException;
import com.josh.hotelmgmt.entities.FoodItem;
import com.josh.hotelmgmt.entities.FoodOrder;
import com.josh.hotelmgmt.repositories.FoodItemsRepository;
import com.josh.hotelmgmt.repositories.FoodOrdersRepository;
import com.josh.hotelmgmt.services.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodOrderServiceImpl implements FoodOrderService {
    @Autowired
    private FoodOrdersRepository foodOrdersRepository;

    @Autowired
    private FoodItemsRepository foodItemsRepository;

    @Override
    public List<FoodOrder> getAllFoodOrders() {
        return foodOrdersRepository.findAll();
    }
    @Override
    public FoodOrder getFoodOrderById(Long foodOrderId) {
        return foodOrdersRepository.findById(foodOrderId).orElse(null);
    }

    @Override
    public void placeFoodOrder(FoodOrder foodOrder) throws FoodNotAvailableException {
        if(!isFoodAvailableForOrder(foodOrder.getFoodItemId().getFoodItemId())){
            throw new FoodNotAvailableException("Food is not available for placing Order");
        }
        //create new food order
        FoodOrder orders = new FoodOrder();
        Optional<FoodItem> foodItem = foodItemsRepository.findById(foodOrder.getFoodItemId().getFoodItemId());
        orders.setFoodItemId(foodOrder.getFoodItemId());
        orders.setOrderId(foodOrder.getOrderId());
        orders.setQuantity(foodOrder.getQuantity());
        orders.setTotalPrice(foodItem.get().getFoodItemPrice() * foodOrder.getQuantity());
        foodOrdersRepository.save(orders);
    }

    @Override
    public void deleteFoodOrder(Long foodOrderId) throws FoodOrderNotFoundException {
        FoodOrder foodOrder = foodOrdersRepository.findById(foodOrderId).orElseThrow(() -> new FoodOrderNotFoundException("Food Order with token"+ foodOrderId + "not available"));
        foodOrdersRepository.delete(foodOrder);
    }

    @Override
    public void updateFoodOrder(Long foodOrderId, FoodOrder updatedfoodOrder) {
        FoodOrder existingFoodOrder = getFoodOrderById(foodOrderId);
        if(existingFoodOrder != null){
            existingFoodOrder.setFoodItemId(updatedfoodOrder.getFoodItemId());
            existingFoodOrder.setQuantity(updatedfoodOrder.getQuantity());
            existingFoodOrder.setTotalPrice(updatedfoodOrder.getTotalPrice());
        }
        foodOrdersRepository.save(existingFoodOrder);
    }

    @Override
    public boolean isFoodAvailableForOrder(Long foodItemId) {
        Optional<FoodItem> foodItem = foodItemsRepository.findById(foodItemId);
        // Return true if at least one food item is found for order otherwise false
        return foodItem.isPresent();
    }
}
