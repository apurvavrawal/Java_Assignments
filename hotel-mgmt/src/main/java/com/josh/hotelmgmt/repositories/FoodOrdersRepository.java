package com.josh.hotelmgmt.repositories;

import com.josh.hotelmgmt.entities.FoodOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodOrdersRepository extends JpaRepository<FoodOrders, Long> {
}
