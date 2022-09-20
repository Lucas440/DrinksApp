package com.example.drinks.DrinkData;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/**
 * Drink CLASS
 *
 * This class is in the database DrinkDB and is an Entity that is stored in it
 *
 * The Entity will auto Generate a Primary key so that it is Unique to each Drink
 * It will also store the title Description and price of the drink
 */
@Entity
public class Drink {
    //An int that stores the Unique ID of the Drink
    @PrimaryKey(autoGenerate = true)
    public  int _uid;
    // A string that stores the Title of the Drink
    @ColumnInfo(name = "title")
    public String _title;
    //A String that stores the description of the drink
    @ColumnInfo(name = "description")
    public String _description;
    //A Float that stores the price of the drink
    @ColumnInfo(name = "price")
    public float _price;

}
