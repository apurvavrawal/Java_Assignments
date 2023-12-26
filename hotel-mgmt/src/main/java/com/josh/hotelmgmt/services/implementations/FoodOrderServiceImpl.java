package com.josh.hotelmgmt.services.implementations;

import com.josh.hotelmgmt.customExceptions.FoodNotAvailableException;
import com.josh.hotelmgmt.customExceptions.FoodOrderNotFoundException;
import com.josh.hotelmgmt.dto.FoodOrderRequest;
import com.josh.hotelmgmt.entities.FoodItem;
import com.josh.hotelmgmt.entities.FoodOrder;
import com.josh.hotelmgmt.entities.Order;
import com.josh.hotelmgmt.repositories.FoodItemsRepository;
import com.josh.hotelmgmt.repositories.FoodOrdersRepository;
import com.josh.hotelmgmt.repositories.OrderRepository;
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

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<FoodOrder> getAllFoodOrders() {
        return foodOrdersRepository.findAll();
    }
    @Override
    public FoodOrder getFoodOrderById(Long foodOrderId) {
        return foodOrdersRepository.findById(foodOrderId).orElse(null);
    }

    @Override
    public void placeFoodOrder(FoodOrderRequest foodOrderRequest) throws FoodNotAvailableException {
        if(!isFoodAvailableForOrder(foodOrderRequest.getFoodItemId())){
            throw new FoodNotAvailableException("Food is not available for placing Order");
        }
        //create new food order
        FoodOrder orders = new FoodOrder();
        Optional<FoodItem> foodItem = foodItemsRepository.findById(foodOrderRequest.getFoodItemId());
        orders.setFoodItem(foodItem.get());
        Optional<Order> order = orderRepository.findById(foodOrderRequest.getOrderId());
        orders.setOrder(order.get());
        orders.setQuantity(foodOrderRequest.getQuantity());
        orders.setTotalPrice(foodItem.get().getFoodItemPrice() * foodOrderRequest.getQuantity());
        foodOrdersRepository.save(orders);
    }

    @Override
    public void deleteFoodOrder(Long foodOrderId) throws FoodOrderNotFoundException {
        FoodOrder foodOrder = foodOrdersRepository.findById(foodOrderId).orElseThrow(() -> new FoodOrderNotFoundException("Food Order with token"+ foodOrderId + "not available"));
        foodOrdersRepository.delete(foodOrder);
    }

    @Override
    public boolean isFoodAvailableForOrder(long foodItemId) {
        FoodItem foodItem = foodItemsRepository.findById(foodItemId).orElse(null);
        // Return true if at least one food item is found for order otherwise false
        return foodItem != null;
    }
}
