package com.example.drinks.DrinkData;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * An abstract class which stores the framework of the database and the database itself
 * This database stores the Drinks class as its entities
 */
@Database(entities = {Drink.class}, version = 1)
public abstract class DrinkDB  extends RoomDatabase{
    //An abstract variable of DrinksDAO called drinksDAO
    public abstract DrinksDAO drinksDAO();
    //A string called DB_NAME which stores the name of the database
    private static final String DB_NAME = "drinks_database_name";
    //A DrinkDB called db
    private static DrinkDB db;

    /**
     * A method that returns the database instance
     * @param context a context used to build the database if its not currently built
     * @return the database instance
     */
    public static DrinkDB getInstance(Context context)
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
    public static  DrinkDB buildDatabaseInstance(Context context)
    {
        //Returns a new built database
        return Room.databaseBuilder(context, DrinkDB.class, DB_NAME).build();
    }
}
