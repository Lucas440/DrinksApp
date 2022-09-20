package com.example.drinks.CustomerData;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * An abstract class which stores the framework of the Database
 * This Database stores the Customer Class as its entities
 */
@Database(entities = {Customer.class}, version = 1)
public abstract class CustomerDB extends RoomDatabase {
    //A abstract variable of CustomerDAO called _customerDAO
    public abstract CustomerDAO _customerDAO();


    private static final String DB_NAME = "Customer_DataBase_Name";
    //A CustomberDB called db
    private static CustomerDB db;

    /**
     * A method that returns the database instance
     * @param context a context used to build the database if its not currently built
     * @return the database instance
     */
    public static CustomerDB getInstance(Context context)
    {
        //if the database is null build the database
        if(db == null) db = buildDatabaseInstance(context);
        //Returns the database
        return db;
    }

    /**
     * A method used to build the database
     * @param context the context of the application
     * @return the built database
     */
    public static CustomerDB buildDatabaseInstance(Context context)
    {
        //Returns a new built database
        return Room.databaseBuilder(context, CustomerDB.class, DB_NAME).build();
    }
}
