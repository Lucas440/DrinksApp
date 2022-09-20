package com.example.drinks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.drinks.DrinkData.Drink;
import com.example.drinks.DrinksOrderData.CustomerOrdersAdapter;
import com.example.drinks.DrinksOrderData.DrinkOrder;
import com.example.drinks.DrinksOrderData.OrderDB;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrder extends AppCompatActivity {

    private OrderDB db;
    CustomerOrdersAdapter adapter;

    int customerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);

        db = Room.databaseBuilder(getApplicationContext(), OrderDB.class, "Order_Database_Name").build();

        RecyclerView recyclerView = findViewById(R.id.CustomerOrder_Recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        customerID = getIntent().getIntExtra("ID", customerID);

        adapter = new CustomerOrdersAdapter();
        recyclerView.setAdapter(adapter);


    }

    public void onResume()
    {
        super.onResume();

        LiveData<List<DrinkOrder>> orders = db.orderDAO().getAll();


        orders.observe(this, new Observer<List<DrinkOrder>>() {
            @Override
            public void onChanged(List<DrinkOrder> drinkOrders) {
                List<DrinkOrder> thisCusOrder = new ArrayList<DrinkOrder>();
                for(DrinkOrder o : drinkOrders)
                {
                    if(o.CustomerID == customerID)
                    {
                        thisCusOrder.add(o);
                    }
                }
                adapter.setOrderList(db, thisCusOrder);
            }
        });
    }
}