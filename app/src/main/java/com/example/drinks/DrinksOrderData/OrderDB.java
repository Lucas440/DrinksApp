package com.example.drinks.DrinksOrderData;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.drinks.DrinkData.DrinkDB;

@Database(entities = {DrinkOrder.class}, version = 1)
public abstract class OrderDB extends RoomDatabase {
    public abstract OrderDAO orderDAO();

    private static final String DB_NAME = "Order_Database_Name";
    private static OrderDB db;

    public static OrderDB getInstance(Context context)
    {
        if(db == null) db = buildDatabaseInstance(context);
        return db;

    }
    public static OrderDB buildDatabaseInstance(Context context)
    {
        return Room.databaseBuilder(context, OrderDB.class, DB_NAME).build();
    }
}
