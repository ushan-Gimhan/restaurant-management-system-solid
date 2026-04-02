package com.restaurant.service;

import com.restaurant.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    // Add a item
    Item addItem(Item item);

    //Update a item
    Item updateItem(Item item);

    //Delete a Item
    void deleteItem(Long id);

    //Get all items
    List<Item> getAllItems();

    //Get by Id
    Optional<Item> getItemById(Long id);

    //Get by name
    List<Item> searchItemsByName(String name);
}
