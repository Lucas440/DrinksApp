package com.example.drinks.DrinksOrderData;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * An abstract class which stores the framework of the Database
 * This Database stores the DrinkOrder Class as its entities
 */
@Database(entities = {DrinkOrder.class}, version = 1)
public abstract class OrderDB extends RoomDatabase {
    //A abstract variable of OrderDAO called _orderDAO
    public abstract OrderDAO _orderDAO();

    //a string  called DB_NAME which stores the name of the database
    private static final String DB_NAME = "Order_Database_Name";
    //A OrderDB called _db
    private static OrderDB _db;

    /**
     * A method that returns the database instance
     * @param context a context used to build the database if its not currently built
     * @return the database instance
     */
    public static OrderDB getInstance(Context context)
    {
        //if the database is null build the database
        if(_db == null) _db = buildDatabaseInstance(context);
        //Returns the database
        return _db;
    }
    /**
     * A method used to build the database
     * @param context the context of the application
     * @return the built database
     */
    public static OrderDB buildDatabaseInstance(Context context)
    {
        //Returns a new built database
        return Room.databaseBuilder(context, OrderDB.class, DB_NAME).build();
    }
}
