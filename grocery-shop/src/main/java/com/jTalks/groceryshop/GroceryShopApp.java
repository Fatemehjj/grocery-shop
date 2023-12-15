package com.jTalks.groceryshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GroceryShopApp {
        public static void main(String[] args) {
            SpringApplication.run(GroceryShopApp.class, args);
        }
}
