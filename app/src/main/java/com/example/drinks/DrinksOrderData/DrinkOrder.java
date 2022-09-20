package com.example.drinks.DrinksOrderData;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DrinkOrder {

    @PrimaryKey(autoGenerate = true)
    public int UID;

    @ColumnInfo(name = "CustomerID")
    public int CustomerID;

    @ColumnInfo(name = "DrinkPrice")
    public float DrinkPrice;

    @ColumnInfo(name = "DrinkName")
    public String DrinkName;

}
