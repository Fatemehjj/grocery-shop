package com.jTalks.groceryshop.controller;

import com.jTalks.groceryshop.dto.GroceriesDto;
import com.jTalks.groceryshop.service.ShopService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("shop")
public class ShopController {
    @Autowired
    private ShopService service;

    @GetMapping("remaining/{grocery}")
    public GroceriesDto getRemainingGrocery(@PathVariable String grocery){
        return service.findRemainingGrocery(grocery);}

    @PutMapping(value = "buy/{number}/{grocery}")
    public GroceriesDto buyGrocery(@PathVariable int number, @PathVariable String grocery){
        return service.buyGrocery(number, grocery);}

    @GetMapping(value = "get/all")
    public ResponseEntity<List<GroceriesDto>> getAll(){
    return service.findAll();
    }
    @GetMapping(value = "/get/{request}")
    public ResponseEntity<String> getPdfOfOrders(@PathVariable String request) throws JRException, FileNotFoundException {
        return service.getPdfOfOrders(request);
    }
}

