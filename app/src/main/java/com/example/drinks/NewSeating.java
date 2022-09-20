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

    CustomerDB db;
    EditText _location, _name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_seating);
        _location = findViewById(R.id.SeatLoaction_EditText);
        _name = findViewById(R.id.PersonsName_Text);

        db = CustomerDB.getInstance(this);
    }

    public void  onSaveClick(View view)
    {
        String locon = _location.getText().toString();
        String name = _name.getText().toString();

        Log.d("Locn", "I am Seat " + locon);

        final Customer customer = new Customer();
        customer._location = locon;
        customer._name = name;

        LiveData<List<Customer>> customers = db._customerDAO().getAll();

        Executor myExectuor = Executors.newSingleThreadExecutor();
        myExectuor.execute(new Runnable() {
            @Override
            public void run() {
                db._customerDAO().insert(customer);
            }
        });
        finish();
    }
}