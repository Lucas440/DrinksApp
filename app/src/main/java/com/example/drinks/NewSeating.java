package com.example.drinks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.drinks.CustomerData.Customer;
import com.example.drinks.CustomerData.CustomerDB;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NewSeating extends AppCompatActivity {

    //A CustomerDB called _db
    CustomerDB _db;
    //EditText variables called _location and _name
    EditText _location, _name;

    /**
     * A method used when the calss is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_seating);
        //INITIALISE CLASS VARIABLES
        //_location
        _location = findViewById(R.id.SeatLoaction_EditText);
        //_name
        _name = findViewById(R.id.PersonsName_Text);
        //_db
        _db = CustomerDB.getInstance(this);
    }

    /**
     * A method that is called when onSaveClick is called
     * @param view the view that called the method
     */
    public void  onSaveClick(View view)
    {
        //A String called locn which gets _locations text
        String locn = _location.getText().toString();
        // A string called name which gets _name's text
        String name = _name.getText().toString();

        //create a new Customer and initialise the entity with method variables
        final Customer customer = new Customer();
        customer._location = locn;
        customer._name = name;

        //Create a new Executor called myExecutor
        Executor myExectuor = Executors.newSingleThreadExecutor();
        myExectuor.execute(new Runnable() {
            /**
             * a method that is used to run the executor
             */
            @Override
            public void run() {
                //inserts the customer into the database
                _db._customerDAO().insert(customer);
            }
        });
        //Finish executing
        finish();
    }
}