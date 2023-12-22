package com.josh.hotelmgmt.repositories;

import com.josh.hotelmgmt.entities.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemsRepository extends JpaRepository<FoodItem, Long> {
}
