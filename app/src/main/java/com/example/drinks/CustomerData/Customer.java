package com.example.drinks.CustomerData;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Customer CLASS
 *
 * This class is in the database CustomerDB and is an Entity that is stored in it
 *
 * The Entity will auto Generate a Primary key so that it is Unique to each customer
 * It will also store the Name of the customer and the Location where they are sitting
 */
@Entity
public class Customer {
    //An int that stores the Unique ID of the customer
    @PrimaryKey(autoGenerate = true)
    public int _uid;
    // A string that stores the location of the customer
    @ColumnInfo(name = "Seat Location")
    public String _location;
    //A String that stores the name of the customer
    @ColumnInfo(name = "Name")
    public String _name;
}
