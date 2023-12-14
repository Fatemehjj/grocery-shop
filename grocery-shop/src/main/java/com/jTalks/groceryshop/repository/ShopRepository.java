package com.jTalks.groceryshop.repository;

import com.jTalks.groceryshop.entity.GroceryShop;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<GroceryShop, Integer> {
    @Query(value = "SELECT q.amount_of_groceries FROM grocery_shop q WHERE q.grocery LIKE (%:grocery%)", nativeQuery = true )
    int findRemainingGroceries(String grocery);
    @Query(value = "SELECT q.grocery FROM grocery_shop q WHERE q.grocery LIKE (%:name%)", nativeQuery = true )
    String groceryName(String name);
    @Modifying
    @Transactional
    @Query(value = "UPDATE grocery_shop q set q.amount_of_groceries=:remainingGroceries where q.grocery LIKE (%:groceryName%)", nativeQuery = true)
    void updateGrocery(String groceryName, int remainingGroceries);

}
