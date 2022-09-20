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

import com.example.drinks.CustomerData.Customer;
import com.example.drinks.CustomerData.CustomerDB;
import com.example.drinks.CustomerData.CustomerListAdapter;
import com.example.drinks.DrinksOrderData.DrinkOrder;
import com.example.drinks.DrinksOrderData.OrderDB;

import java.util.List;

/**
 * A class which shows the Seats and allows to add new customers to the seats
 */
public class SeatSelection extends AppCompatActivity {

    //A CustomerListAdapter called _customerListAdapter
    CustomerListAdapter _customerListAdapter;
    //A CustomerDB called _customerDB
    CustomerDB _customerDB;
    //A OrderDB called _orderDB
    OrderDB _orderDB;

    /**
     * A method that is called when the class is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);
        //INITIALISE CLASS VARIABLES
        //_customerDB
        _customerDB = Room.databaseBuilder(getApplicationContext(), CustomerDB.class, "Customer_DataBase_Name").build();
        //_orderDB
        _orderDB = Room.databaseBuilder(getApplicationContext(), OrderDB.class, "Order_Database_Name").build();
        //_customerListAdapter
        _customerListAdapter = new CustomerListAdapter();

        //INITIALISE a new RecyclerView
        RecyclerView recyclerView = findViewById(R.id.Customer_Recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(_customerListAdapter);
    }

    /**
     * A method that starts the newSeating activity
     * @param view the view that called the method
     */
    public void onAddSeatClick(View view)
    {
        //Starts a NewSeating class
        startActivity(new Intent(SeatSelection.this, NewSeating.class));
        //Resumes this activity
        this.onResume();
    }

    /**
     * A method that is called when the class is resumed
     */
    public void onResume()
    {
        super.onResume();
        //Gets the instances of the databases
        //CustomerDB
        LiveData<List<Customer>> customers = _customerDB._customerDAO().getAll();
        //OrderDB
        LiveData<List<DrinkOrder>> orders = _orderDB._orderDAO().getAll();
        //Observes customer database
        customers.observe(this, new Observer<List<Customer>>() {
            /**
             * this method is called if the database has changed
             * @param customers a list of current customers
             */
            @Override
            public void onChanged(List<Customer> customers) {
                //Updates customerListAdapter
                _customerListAdapter.setCustomerList(_customerDB, customers);
            }
        });
        //Observes orders database
        orders.observe(this, new Observer<List<DrinkOrder>>() {
            /**
             * this method is called if the database has changed
             * @param drinkOrders a list of current drinkOrders
             */
            @Override
            public void onChanged(List<DrinkOrder> drinkOrders) {
                //Updates customerListAdapter
                _customerListAdapter.setOrderList(_orderDB, drinkOrders);
            }
        });
    }
}