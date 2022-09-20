package com.example.drinks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * This is the Main and is the first screen the user will see
 */
public class MainActivity extends AppCompatActivity {


    /**
     * A method that is called when the class is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * A method that starts the Seating activity
     * @param view The view that caused the method to be called
     */
    public void onSeatingClick(View view)
    {
        //Starts the seatSelection activity
        startActivity(new Intent(MainActivity.this, SeatSelection.class));
    }

    /**
     * A method that starts the ResetApp activity
     * @param view The view that caused the method to be called
     */
    public void onDBRestartClick(View view) {
        //Starts the ResetApp class
        startActivity(new Intent(MainActivity.this, ResetApp.class));
    }

    /**
     *  A method that starts the DrinkOption activity
     * @param view The view that caused the method to be called
     */
    public void onCreateDrinkClick(View view)
    {
            //Starts the DrinkOption class
            startActivity(new Intent(MainActivity.this, DrinkOptionActivity.class));
        }
    }
