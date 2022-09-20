package com.example.drinks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.drinks.DrinkData.Drink;
import com.example.drinks.DrinkData.DrinkDB;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * A class used to create new drinks for the database
 */
public class CreateNewDrink extends AppCompatActivity {

    //A Button called _saveDrink
    Button _saveDrink;
    //A DrinkDB called _db
    DrinkDB _db;
    //EditText Variables called _titleText, _descText and _priceText
    EditText _titleText, _descText, _priceText;

    /**
     * A method that is called when the class is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_drink);
        //INITIALISE CLASS VARIABLES
        //_titleText
        _titleText = findViewById(R.id.DrinkName_EditText);
        //_descText
        _descText = findViewById(R.id.drinkDecs_EditText);
        //_priceText
        _priceText = findViewById(R.id.DrinkPrice_EditNumber);
        //_db
        _db = DrinkDB.getInstance(this);

        //_saveDrink
        _saveDrink = (Button) findViewById(R.id.Finish_Button);
        _saveDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveClick();
            }
        });

    }

    /**
     * A method used when the saveClick button is clicked
     */
    private void saveClick()
    {
        //INITIALISE variables by getting the texted stored in the view
        String title = _titleText.getText().toString();
        String desc = _descText.getText().toString();
        float price = Float.parseFloat(_priceText.getText().toString());

        //INITIALISE a new Drink called drink
        final Drink drink = new Drink();
        //Set the data in drink to the method variables
        drink._title = title;
        drink._description = desc;
        drink._price = price;

        //Create a new Executor called myExecutor
        Executor myExecutor = Executors.newSingleThreadExecutor();
        //Execute the following
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                //insert the drink into the database
                _db.drinksDAO().insert(drink);
            }
        });
        //Finish executing
        finish();
    }
}