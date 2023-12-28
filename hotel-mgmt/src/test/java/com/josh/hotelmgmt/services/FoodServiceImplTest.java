package com.josh.hotelmgmt.services;

import com.josh.hotelmgmt.customExceptions.FoodNotAvailableException;
import com.josh.hotelmgmt.entities.FoodItem;
import com.josh.hotelmgmt.repositories.FoodItemsRepository;
import com.josh.hotelmgmt.services.implementations.FoodServiceImpl;
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
public class FoodServiceImplTest {
    @Mock
    private FoodItemsRepository foodItemsRepository;

    @InjectMocks
    private FoodServiceImpl foodServiceImpl;

    @Test
    void testGetAllFoodItems() {
        when(foodItemsRepository.findAll()).thenReturn(List.of(new FoodItem(), new FoodItem()));
        assertEquals(2, foodServiceImpl.getAllFoodItems().size());
    }

    @Test
    void testGetFoodItemById() {
        when(foodItemsRepository.findById(anyLong())).thenReturn(Optional.of(new FoodItem()));
        assertNotNull(foodServiceImpl.getFoodItemById(1L));
    }

    @Test
    void testCreateNewFoodEntry() {

        FoodItem foodItem = new FoodItem();
        foodItem.setFoodItemName("Poha");
        foodItem.setFoodItemPrice(10.99);
        when(foodItemsRepository.save(new FoodItem())).thenReturn(foodItem);

        assertDoesNotThrow(() -> foodServiceImpl.createNewFoodEntry(foodItem));
    }

    @Test
    void testDeleteFoodItem() {
        when(foodItemsRepository.findById(anyLong())).thenReturn(Optional.of(new FoodItem()));
        assertDoesNotThrow(() -> foodServiceImpl.deleteFoodItem(1L));
    }

    @Test
    void testDeleteFoodItemThrowsFoodNotAvailableException() {
        when(foodItemsRepository.findById(anyLong())).thenReturn(Optional.empty());
        FoodNotAvailableException exception = assertThrows(FoodNotAvailableException.class,
                () -> foodServiceImpl.deleteFoodItem(1L));
        assertEquals("Food Item with Id: 1 is not available", exception.getMessage());
    }
}
