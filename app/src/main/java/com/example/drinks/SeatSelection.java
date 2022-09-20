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

public class SeatSelection extends AppCompatActivity {

    CustomerListAdapter customerListAdapter;
    CustomerDB customerDB;

    OrderDB orderDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);

        customerDB = Room.databaseBuilder(getApplicationContext(), CustomerDB.class, "Customer_DataBase_Name").build();
        orderDB = Room.databaseBuilder(getApplicationContext(), OrderDB.class, "Order_Database_Name").build();

        RecyclerView recyclerView = findViewById(R.id.Customer_Recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        customerListAdapter = new CustomerListAdapter();
        recyclerView.setAdapter(customerListAdapter);
    }

    public void onAddSeatClick(View view)
    {
        startActivity(new Intent(SeatSelection.this, NewSeating.class));
        this.onResume();
    }



    public void onResume()
    {
        super.onResume();

        LiveData<List<Customer>> customers = customerDB._customerDAO().getAll();

        LiveData<List<DrinkOrder>> orders = orderDB._orderDAO().getAll();

        customers.observe(this, new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
                customerListAdapter.setCustomerList(customerDB, customers);
            }
        });

        orders.observe(this, new Observer<List<DrinkOrder>>() {
            @Override
            public void onChanged(List<DrinkOrder> drinkOrders) {
                customerListAdapter.setOrderList(orderDB, drinkOrders);
            }
        });
    }
}