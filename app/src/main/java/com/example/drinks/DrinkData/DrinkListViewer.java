package com.example.drinks.DrinkData;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.drinks.R;

/**
 * A class used to display the DrinkList
 */
public class DrinkListViewer extends AppCompatActivity {

    /**
     * a method that is called when the class is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_list_viewer);
    }
}