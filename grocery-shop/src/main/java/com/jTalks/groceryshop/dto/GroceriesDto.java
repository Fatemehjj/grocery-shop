package com.jTalks.groceryshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroceriesDto implements Serializable {
    String grocery;
    int quantity;
    int price;
}
