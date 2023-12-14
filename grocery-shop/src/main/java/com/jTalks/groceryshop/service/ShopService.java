package com.jTalks.groceryshop.service;

import com.jTalks.groceryshop.dto.GroceriesDto;
import com.jTalks.groceryshop.entity.GroceryShop;
import com.jTalks.groceryshop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {
    @Autowired
    private ShopRepository repository;

    public ResponseEntity<String> findRemainingGrocery(String grocery) {
        String groceryName = repository.groceryName(grocery);
        if (groceryName != null) {
            int remaining = repository.findRemainingGroceries(grocery);
            return new ResponseEntity<>("remaining " + grocery + "s are " + remaining, HttpStatus.OK);
        }
        if (grocery.equals("null")) {
            return new ResponseEntity<>("not allowed !", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("we ran out of " + grocery + " !", HttpStatus.BAD_REQUEST);
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
}
