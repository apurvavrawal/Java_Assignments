package com.josh.hotelmgmt.services;

import com.josh.hotelmgmt.customExceptions.OrderNotFoundException;
import com.josh.hotelmgmt.entities.FoodItem;
import com.josh.hotelmgmt.entities.FoodOrder;
import com.josh.hotelmgmt.entities.Order;
import com.josh.hotelmgmt.repositories.FoodOrdersRepository;
import com.josh.hotelmgmt.repositories.OrderRepository;
import com.josh.hotelmgmt.services.implementations.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private FoodOrdersRepository foodOrdersRepository;


    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    @Test
    void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(List.of(new Order(), new Order()));
        assertEquals(2, orderServiceImpl.getAllOrders().size());
    }

    @Test
    void testGetOrderByOrderId() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(new Order()));
        assertNotNull(orderServiceImpl.getOrderByOrderId(1L));
    }

    @Test
    void testCreateOrder() {
        Order order = new Order();
        order.setOrderId(1L);
        order.setOrderTotal(0.0);

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        assertDoesNotThrow(() -> orderServiceImpl.createOrder());
        verify(orderRepository).save(any(Order.class));

    }

    @Test
    void testGetListOfDetailedFoodOrderByOrderId() {
        FoodOrder foodOrder = new FoodOrder();
        foodOrder.setFoodOrderId(111);
        foodOrder.setOrder(new Order(1, 0.00));
        foodOrder.setFoodItem(new FoodItem(1,"Poha",50.00));
        foodOrder.setQuantity(2);
        foodOrder.setTotalPrice(100.00);

        when(foodOrdersRepository.findAllById(Collections.singleton(anyLong()))).thenReturn(List.of(foodOrder));
        assertNotNull(orderServiceImpl.getListOfDetailedFoodOrderByOrderId(1L));
    }

    @Test
    void testDeleteTotalOrder() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(new Order()));
        assertDoesNotThrow(() -> orderServiceImpl.deleteTotalOrder(1L));
    }

    @Test
    void testDeleteTotalOrderThrowsOrderNotFoundException() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());
        OrderNotFoundException exception = assertThrows(OrderNotFoundException.class,
                () -> orderServiceImpl.deleteTotalOrder(1L));
        assertEquals("Order with order Id: 1 not found", exception.getMessage());
    }

    @Test
    void testCreateTotalOrder() {
        Order order = new Order();
        order.setOrderId(1);
        order.setOrderTotal(100.00);

        when(foodOrdersRepository.findById(anyLong())).thenReturn(Optional.of(new FoodOrder()));
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

        assertDoesNotThrow(() -> orderServiceImpl.createTotalOrder(1L));
        verify(orderRepository).save(order);
    }
}
