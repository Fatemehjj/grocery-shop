package com.jTalks.groceryshop.controller;

import com.jTalks.groceryshop.dto.GroceriesDto;
import com.jTalks.groceryshop.entity.GroceryShop;
import com.jTalks.groceryshop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shop")
public class ShopController {
    @Autowired
    private ShopService service;
@GetMapping("remaining/{grocery}")
    public String getRemainingGrocery(@PathVariable String grocery){

        return service.findRemainingGrocery(grocery);}

    @PutMapping(value = "buy/{number}/{grocery}")
    public ResponseEntity<String> buyGrocery(@PathVariable int number, @PathVariable String grocery){
        return service.buyGrocery(number, grocery);}

    @GetMapping(value = "get/all")
    public ResponseEntity<List<GroceriesDto>> getAll(){
    return service.findAll();
    }

    @GetMapping("info/{grocery}")
    public GroceriesDto getInfoForGrocery(@PathVariable String grocery){

        return service.getInfoForGrocery(grocery);}

}

