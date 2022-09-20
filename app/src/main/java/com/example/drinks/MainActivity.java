package com.example.drinks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button _drinksButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onSettingClick(View view)
    {
        startActivity(new Intent(MainActivity.this, SeatSelection.class));
    }

    public void onDBRestartClick(View view) {
        startActivity(new Intent(MainActivity.this, ResetApp.class));
    }

    public void onCreateDrinkClick(View view)
    {
            startActivity(new Intent(MainActivity.this, DrinkOptionActivity.class));
        }
    }
