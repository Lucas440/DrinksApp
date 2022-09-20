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

/**
 * A class which shows the current drink options available
 */
public class DrinkOptionActivity extends AppCompatActivity {

    //A DrinksListAdapter called _drinksListAdapter
    DrinkListAdapter _drinkListAdapter;
    //A Drinks db called _db
    DrinkDB _db;

    /**
     * A method called when this class is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_option);
        //INITIALISE CLASS VARIABLES
        //_db
        _db = Room.databaseBuilder(getApplicationContext() , DrinkDB.class, "drinks_database_name").build();
        //_drinksListAdapter
        _drinkListAdapter = new DrinkListAdapter();

        //INITIALISE a recyclerView
        RecyclerView recyclerView = findViewById(R.id.DrinksRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(_drinkListAdapter);

        }

    /**
     * A method used to respond to a create new drink click
     * @param view the view that called the method
     */
    public void onCreateDrinkClick(View view)
    {
        //Starts a new intent which creates the CreateNewDrink class
        startActivity(new Intent(DrinkOptionActivity.this, CreateNewDrink.class));
        //Resumes the activity
        this.onResume();
    }

    /**
     *  A method that is called when the class resumes
     */
        public void onResume()
        {
            super.onResume();
            //Gets the current database instance
            LiveData<List<Drink>> drinks = _db.drinksDAO().getAll();

            //Observes the database
            drinks.observe(this, new Observer<List<Drink>>() {
                /**
                 * A method that responds when the database changed
                 * @param drinks a list of Drink
                 */
                @Override
                public void onChanged(List<Drink> drinks) {
                    //Sets the Drinks list in the adapter
                    _drinkListAdapter.setDrinksList(_db, drinks);
                }
            });
        }
    }