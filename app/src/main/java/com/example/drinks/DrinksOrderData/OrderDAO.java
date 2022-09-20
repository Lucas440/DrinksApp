package com.example.drinks.DrinksOrderData;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OrderDAO {
    @Query("SELECT * FROM DrinkOrder")
    LiveData<List<DrinkOrder>> getAll();

    @Insert
    void insert(DrinkOrder order);
}
