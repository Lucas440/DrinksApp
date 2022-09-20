package com.example.drinks.CustomerData;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.drinks.R;

/**
 * A Class used to display the current View
 */
public class CustomerListViewer extends AppCompatActivity {

    /**
     * A Method that is called when the class is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list_viewer);
    }
}