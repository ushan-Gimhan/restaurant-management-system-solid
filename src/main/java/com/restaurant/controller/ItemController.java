package com.restaurant.controller;

import com.restaurant.entity.Item;
import com.restaurant.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
@CrossOrigin
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    //CREATE
    @PostMapping("/create")
    public Item addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    //GET ALL
    @GetMapping("/getAllItems")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    //GET BY ID
    @GetMapping("/{id}")
    public Optional<Item> getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    //UPDATE
    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
        item.setId(id);
        return itemService.updateItem(item);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return "Item deleted successfully!";
    }

    //SEARCH
    @GetMapping("/search")
    public List<Item> searchItems(@RequestParam String name) {
        return itemService.searchItemsByName(name);
    }
}