package com.jTalks.groceryshop.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Entity
@Data
@Table(name = "grocery_shop")
public class GroceryShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "grocery")
    private String groceryName;

    @NotNull
    private int price;

    @Column(name = "amount_of_groceries")
    private int numberOfGroceries;

}
