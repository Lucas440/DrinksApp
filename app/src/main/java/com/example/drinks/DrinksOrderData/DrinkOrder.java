package com.example.drinks.DrinksOrderData;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * DrinkOrder Class
 *
 * This class is an entity in the OrderDB
 *
 * This class generates a Primary key and stores the customersID
 * Price of a drink and the name of the drink
 */
@Entity
public class DrinkOrder {

    //An int which stores the UID of the order
    @PrimaryKey(autoGenerate = true)
    public int _uid;
    //An int that stores the CustomersID
    @ColumnInfo(name = "CustomerID")
    public int _customerID;
    //A float that stores the Drinks Price
    @ColumnInfo(name = "DrinkPrice")
    public float _drinkPrice;
    //A String that stores the drinks name
    @ColumnInfo(name = "DrinkName")
    public String _drinkName;

}
