package com.example.drinks.CustomerData;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.drinks.CustomerData.Customer;

import java.util.List;

/**
 * CustomerDAO is an interface that is used by the CustomerDB class
 *
 * This is a DAO that is used to get the data from the database as well as insert new entries
 *
 */
@Dao
public interface CustomerDAO {
    /**
     * A method that will return all the customers within the database
     * @return The livedata of the database with a List
     */
    @Query("SELECT * FROM Customer")
    LiveData<List<Customer>> getAll();

    /**
     * A Method used to insert a new customer into the database
     * @param customer the customer that is being added to the database
     */
    @Insert
    void insert(Customer customer);
}
