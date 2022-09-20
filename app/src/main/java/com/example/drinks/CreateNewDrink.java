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

public class CreateNewDrink extends AppCompatActivity {

    Button _saveDrink;
    DrinkDB db;
    EditText titleText, descText, priceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_drink);

        titleText = findViewById(R.id.DrinkName_EditText);
        descText = findViewById(R.id.drinkDecs_EditText);
        priceText = findViewById(R.id.DrinkPrice_EditNumber);

        db = DrinkDB.getInstance(this);


        _saveDrink = (Button) findViewById(R.id.Finish_Button);
        _saveDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveClick();
            }
        });

    }

    private void saveClick()
    {
        String title = titleText.getText().toString();
        String desc = descText.getText().toString();
        float price = Float.parseFloat(priceText.getText().toString());


        final Drink drink = new Drink();
        drink._title = title;
        drink._description = desc;
        drink._price = price;

        LiveData<List<Drink>> tasks = db.drinksDAO().getAll();

        tasks.observe(this, new Observer<List<Drink>>() {
            @Override
            public void onChanged(List<Drink> drinks) {
                Log.d("ToDoApp" , drink._title + " : " + drink._description + ":" + drink._price);

            }
        });
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                db.drinksDAO().insert(drink);
            }
        });
        finish();
    }
}