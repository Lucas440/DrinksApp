package com.example.drinks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.drinks.DrinksOrderData.CustomerOrdersAdapter;
import com.example.drinks.DrinksOrderData.DrinkOrder;
import com.example.drinks.DrinksOrderData.OrderDB;

import java.util.ArrayList;
import java.util.List;

/**
 * A class which displays a customer's orders
 */
public class CustomerOrder extends AppCompatActivity {

    //A OrderDB called _db
    private OrderDB _db;
    // A CustomerOrdersAdapter called _adapter
    CustomerOrdersAdapter _adapter;
    //A int which stores te customers id called _customerID
    int _customerID;

    /**
     * A Method called when the class is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);
        //INITIALISE CLASS VARIABLES
        //_db
        _db = Room.databaseBuilder(getApplicationContext(), OrderDB.class, "Order_Database_Name").build();
        //_customerID
        _customerID = getIntent().getIntExtra("ID", _customerID);
        //_adapter
        _adapter = new CustomerOrdersAdapter();

        //Creates and initialise a new RecyclerView
        RecyclerView recyclerView = findViewById(R.id.CustomerOrder_Recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(_adapter);
    }

    /**
     * A Method that is called when the class resumes
     */
    public void onResume()
    {
        super.onResume();

        //Gets the current instance of the orders database
        LiveData<List<DrinkOrder>> orders = _db._orderDAO().getAll();

        //Observes the database
        orders.observe(this, new Observer<List<DrinkOrder>>() {
            /**
             * A method called when the database changes
             * @param drinkOrders a list of drink orders
             */
            @Override
            public void onChanged(List<DrinkOrder> drinkOrders) {
                //Creates a new array list
                List<DrinkOrder> thisCusOrder = new ArrayList<DrinkOrder>();
                //Loops over each order
                for(DrinkOrder o : drinkOrders)
                {
                    //if the orders customer id matches current customer id this is true
                    if(o._customerID == _customerID)
                    {
                        //Adds it to the list
                        thisCusOrder.add(o);
                    }
                }
                //Sets the order list in adapter
                _adapter.setOrderList(_db, thisCusOrder);
            }
        });
    }
}