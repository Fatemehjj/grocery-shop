package com.jTalks.groceryshop.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroceriesDto implements Serializable {
    String grocery_name;
    int number_of_groceries;
    int price;
}
