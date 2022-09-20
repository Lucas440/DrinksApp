package com.example.drinks.DrinksOrderData;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * OrderDAO is an interface that is used by the CustomerDB class
 *
 * This is a DAO that is used to get the data from the database as well as insert new entries
 *
 */
@Dao
public interface OrderDAO {
    /**
     * A method that will return all the customers within the database
     * @return The livedata of the database with a List
     */
    @Query("SELECT * FROM DrinkOrder")
    LiveData<List<DrinkOrder>> getAll();

    /**
     * A Method used to insert a new customer into the database
     * @param order the order being added
     */
    @Insert
    void insert(DrinkOrder order);
}
