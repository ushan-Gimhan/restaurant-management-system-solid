package com.restaurant.repository;


import com.restaurant.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // Search items by name
    List<Item> findByNameContainingIgnoreCase(String name);
}
