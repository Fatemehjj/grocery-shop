package com.jTalks.groceryshop.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Table(name = "grocery_shop")
public class GroceryShop implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String grocery;
    @NotNull
    private double price;
    private int quantity;

}
