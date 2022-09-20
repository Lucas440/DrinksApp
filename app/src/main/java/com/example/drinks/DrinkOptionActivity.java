package com.example.drinks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.drinks.DrinkData.Drink;
import com.example.drinks.DrinkData.DrinkDB;
import com.example.drinks.DrinkData.DrinkListAdapter;

import java.util.List;

public class DrinkOptionActivity extends AppCompatActivity {

    DrinkListAdapter drinkListAdapter;
    DrinkDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_option);

        db = Room.databaseBuilder(getApplicationContext() , DrinkDB.class, "drinks_database_name").build();

        RecyclerView recyclerView = findViewById(R.id.DrinksRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        drinkListAdapter = new DrinkListAdapter();
        recyclerView.setAdapter(drinkListAdapter);

        }

    public void onCreateDrinkClick(View view)
    {
        startActivity(new Intent(DrinkOptionActivity.this, CreateNewDrink.class));
        this.onResume();
    }

        public void onResume()
        {
            super.onResume();

            LiveData<List<Drink>> drinks = db.drinksDAO().getAll();

            LinearLayout linearLayout = findViewById(R.id.AddOrderLayout);

            drinks.observe(this, new Observer<List<Drink>>() {
                @Override
                public void onChanged(List<Drink> drinks) {
                    drinkListAdapter.setDrinksList(db, drinks);
                }
            });
        }
    }