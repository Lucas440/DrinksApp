package com.example.drinks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

/**
 * A class used to reset the app
 */
public class ResetApp extends AppCompatActivity {

    /**
     * A method used when the class is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_app);
    }

    /**
     * A method that deletes the customer and order database
     * @param view the view that called this method
     */
    public void onClickResetSeats(View view)
    {
        //Deletes the customer and order database
        this.deleteDatabase("Customer_DataBase_Name");
        this.deleteDatabase("Order_Database_Name");
    }
    /**
     *A method that deletes the drinks database
     * @param view the view that called this method
     */
    public void onClickResetDrinks(View view)
    {
        //deletes the drinks database
        this.deleteDatabase("drinks_database_name");

    }
}