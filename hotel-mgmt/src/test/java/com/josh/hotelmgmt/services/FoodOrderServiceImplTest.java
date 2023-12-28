package com.josh.hotelmgmt.services;

import com.josh.hotelmgmt.customExceptions.FoodNotAvailableException;
import com.josh.hotelmgmt.customExceptions.FoodOrderNotFoundException;
import com.josh.hotelmgmt.dto.FoodOrderRequest;
import com.josh.hotelmgmt.entities.FoodItem;
import com.josh.hotelmgmt.entities.FoodOrder;
import com.josh.hotelmgmt.entities.Order;
import com.josh.hotelmgmt.repositories.FoodItemsRepository;
import com.josh.hotelmgmt.repositories.FoodOrdersRepository;
import com.josh.hotelmgmt.repositories.OrderRepository;
import com.josh.hotelmgmt.services.implementations.FoodOrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FoodOrderServiceImplTest {
    @Mock
    private FoodOrdersRepository foodOrdersRepository;

    @Mock
    private FoodItemsRepository foodItemsRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private FoodOrderServiceImpl foodOrderServiceImpl;

    @Test
    void testGetAllFoodOrders() {
        when(foodOrdersRepository.findAll()).thenReturn(List.of(new FoodOrder(), new FoodOrder()));
        assertEquals(2, foodOrderServiceImpl.getAllFoodOrders().size());
    }

    @Test
    void testGetFoodOrderById() {
        when(foodOrdersRepository.findById(anyLong())).thenReturn(Optional.of(new FoodOrder()));
        assertNotNull(foodOrderServiceImpl.getFoodOrderById(1L));
    }

    @Test
    void testPlaceFoodOrder() {
        when(foodItemsRepository.findById(anyLong())).thenReturn(Optional.of(new FoodItem()));
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(new Order()));

        FoodOrderRequest foodOrderRequest = new FoodOrderRequest();
        foodOrderRequest.setFoodItemId(1L);
        foodOrderRequest.setOrderId(2L);
        foodOrderRequest.setQuantity(3);

        when(foodOrdersRepository.save(new FoodOrder(1,new FoodItem(),new Order(),2,30.00))).thenReturn(new FoodOrder());

        assertDoesNotThrow(() -> foodOrderServiceImpl.placeFoodOrder(foodOrderRequest));
    }

    @Test
    void testPlaceFoodOrderThrowsFoodNotAvailableException() {
        when(foodItemsRepository.findById(anyLong())).thenReturn(Optional.empty());

        FoodOrderRequest foodOrderRequest = new FoodOrderRequest();
        foodOrderRequest.setFoodItemId(1L);
        foodOrderRequest.setOrderId(2L);
        foodOrderRequest.setQuantity(3);

        FoodNotAvailableException exception = assertThrows(FoodNotAvailableException.class,
                () -> foodOrderServiceImpl.placeFoodOrder(foodOrderRequest));

        assertEquals("Food is not available for placing Order", exception.getMessage());
    }

    @Test
    void testDeleteFoodOrder() {
        when(foodOrdersRepository.findById(anyLong())).thenReturn(Optional.of(new FoodOrder()));
        assertDoesNotThrow(() -> foodOrderServiceImpl.deleteFoodOrder(1L));
    }

    @Test
    void testDeleteFoodOrderThrowsFoodOrderNotFoundException() {
        when(foodOrdersRepository.findById(anyLong())).thenReturn(Optional.empty());
        FoodOrderNotFoundException exception = assertThrows(FoodOrderNotFoundException.class,
                () -> foodOrderServiceImpl.deleteFoodOrder(1L));
        assertEquals("Food Order with foodOrderId: 1 not available", exception.getMessage());
    }

    @Test
    void testIsFoodAvailableForOrder() {
        when(foodItemsRepository.findById(anyLong())).thenReturn(Optional.of(new FoodItem()));
        assertTrue(foodOrderServiceImpl.isFoodAvailableForOrder(1L));
    }

    @Test
    void testIsFoodAvailableForOrderReturnsFalse() {
        when(foodItemsRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertFalse(foodOrderServiceImpl.isFoodAvailableForOrder(1L));
    }
}

