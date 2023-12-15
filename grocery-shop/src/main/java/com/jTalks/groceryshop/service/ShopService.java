package com.jTalks.groceryshop.service;

import com.jTalks.groceryshop.dto.GroceriesDto;
import com.jTalks.groceryshop.entity.GroceryShop;
import com.jTalks.groceryshop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService {
    private static final String REDIS_CACHE_VALUE = "orders";
    @Autowired
    private ShopRepository repository;
    @Cacheable(value = REDIS_CACHE_VALUE, key = "#grocery")
    public String findRemainingGrocery(String grocery){
        System.out.println("getting from database");
        String groceryName = repository.groceryName(grocery);
        if (groceryName != null) {
            int remaining = repository.findRemainingGroceries(grocery);
            return "remaining " + grocery + "s are " + remaining;
        }
        if (grocery.equals("null")) {
            return "not allowed !";
        }
        return "we ran out of " + grocery + " !";

    }

    public ResponseEntity<String> buyGrocery(int numberOfGrocery, String grocery) {
        String findGroceryName = repository.groceryName(grocery);
        if (findGroceryName != null) {
            int remaining = repository.findRemainingGroceries(grocery);
            int groceryStore = remaining - numberOfGrocery;
            if (groceryStore >= 0) {
                repository.updateGrocery(grocery, groceryStore);
                return new ResponseEntity<>("you bought " + numberOfGrocery + "  " + grocery, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("we don't have "+numberOfGrocery+"  "+grocery,HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<GroceriesDto>> findAll() {
        List<GroceryShop> allGroceries = repository.findAll();
        List<GroceriesDto> groceries = new ArrayList<>();
        for (GroceryShop groceryShop : allGroceries){
            GroceriesDto shop = new GroceriesDto();
            shop.setGrocery_name(groceryShop.getGroceryName());
            shop.setPrice(groceryShop.getPrice());
            shop.setNumber_of_groceries(groceryShop.getNumberOfGroceries());

            groceries.add(shop);
        }
        return new ResponseEntity<>(groceries, HttpStatus.OK);
    }
@Cacheable(value = REDIS_CACHE_VALUE, key = "#grocery")
    public GroceriesDto getInfoForGrocery(String grocery) {
    System.out.println("getting from db");
        Optional<GroceryShop> findGrocery = repository.findByGroceryName(grocery);
        GroceriesDto wrapper = new GroceriesDto();
        wrapper.setGrocery_name(findGrocery.get().getGroceryName());
        wrapper.setPrice(findGrocery.get().getPrice());
        wrapper.setNumber_of_groceries(findGrocery.get().getNumberOfGroceries());

        return wrapper;
    }
}
