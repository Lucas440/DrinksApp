package com.example.drinks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ResetApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_app);
    }

    public void onClickResetSeats(View view)
    {
        this.deleteDatabase("Customer_DataBase_Name");
        this.deleteDatabase("Order_Database_Name");
    }

    public void onClickResetDrinks(View view)
    {
        this.deleteDatabase("drinks_database_name");
    }
}