package com.jTalks.groceryshop.service;

import com.jTalks.groceryshop.dto.GroceriesDto;
import com.jTalks.groceryshop.entity.GroceryShop;
import com.jTalks.groceryshop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
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
    public GroceriesDto findRemainingGrocery(String grocery){
        System.out.println("getting remaining grocery from database");
        String groceryName = repository.groceryName(grocery);
        if (groceryName != null) {
            GroceriesDto findGrocery = new GroceriesDto();
            int remaining = repository.findRemainingGroceries(grocery);
            findGrocery.setGrocery(groceryName);
            findGrocery.setQuantity(remaining);
            findGrocery.setPrice(getInfoForGrocery(groceryName).getQuantity());
            return findGrocery;
        }
        if (grocery.equals("null")) {
            return new GroceriesDto("none", 0, 0);
        }
        return new GroceriesDto(grocery+" not found", 0, 0);

    }
    @CachePut(value = REDIS_CACHE_VALUE, key = "#grocery")
    public GroceriesDto buyGrocery(int numberOfGrocery, String grocery) {
        System.out.println("getting data from db");
        String findGroceryName = repository.groceryName(grocery);
        if (findGroceryName != null) {
            int remaining = repository.findRemainingGroceries(grocery);
            int groceryStore = remaining - numberOfGrocery;
            if (groceryStore >= 0) {
                GroceriesDto groceriesInfo = new GroceriesDto();
                repository.updateGrocery(grocery, groceryStore);
                Optional<GroceryShop> groceryObj = repository.findByGroceryName(findGroceryName);
                groceriesInfo.setGrocery(groceryObj.get().getGroceryName());
                groceriesInfo.setQuantity(groceryObj.get().getNumberOfGroceries());
                groceriesInfo.setPrice(groceryObj.get().getPrice());

               return groceriesInfo;
            }
        }
       return  new GroceriesDto("not found", 0, 0);
    }


    public ResponseEntity<List<GroceriesDto>> findAll() {
        List<GroceryShop> allGroceries = repository.findAll();
        List<GroceriesDto> groceries = new ArrayList<>();
        for (GroceryShop groceryShop : allGroceries){
            GroceriesDto shop = new GroceriesDto();
            shop.setGrocery(groceryShop.getGroceryName());
            shop.setPrice(groceryShop.getPrice());
            shop.setQuantity(groceryShop.getNumberOfGroceries());

            groceries.add(shop);
        }
        return new ResponseEntity<>(groceries, HttpStatus.OK);
    }
@Cacheable(value = REDIS_CACHE_VALUE, key = "#grocery")
    public GroceriesDto getInfoForGrocery(String grocery) {
    System.out.println("getting info from db");
        Optional<GroceryShop> findGrocery = repository.findByGroceryName(grocery);
        GroceriesDto wrapper = new GroceriesDto();
        wrapper.setGrocery(findGrocery.get().getGroceryName());
        wrapper.setPrice(findGrocery.get().getPrice());
        wrapper.setQuantity(findGrocery.get().getNumberOfGroceries());

        return wrapper;
    }
}
