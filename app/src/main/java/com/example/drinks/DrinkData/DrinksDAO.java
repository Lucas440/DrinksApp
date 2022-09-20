package com.example.drinks.DrinkData;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.drinks.DrinkData.Drink;

import java.util.List;

@Dao
public interface DrinksDAO {
    @Query("SELECT * FROM Drink")
    LiveData<List<Drink>> getAll();

    @Insert
    void insert(Drink drink);
}
