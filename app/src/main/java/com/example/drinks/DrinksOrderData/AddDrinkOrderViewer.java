package com.example.drinks.DrinksOrderData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.drinks.DrinkData.Drink;
import com.example.drinks.DrinkData.DrinkDB;
import com.example.drinks.R;

import java.util.List;

/**
 * A class used to add a new Drink order
 */
public class AddDrinkOrderViewer extends AppCompatActivity {

    //A DrinkOrderRecyler called _drinkOrderRecycler
    DrinkOrderRecyler _drinkOrderRecycler;
    //A DrinkDB called _db
    DrinkDB _db;
    //A OrderDB called orderDB
    private OrderDB _orderDB;
    //A int called _customerID
    private int _customerID;

    /**
     * A method that is called when the class is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_order_recyler);

        //INITIALISE class variables
        //_db
        _db = Room.databaseBuilder(getApplicationContext(), DrinkDB.class, "drinks_database_name").build();
        //_orderDB
        _orderDB = Room.databaseBuilder(getApplicationContext(), OrderDB.class, "Order_Database_Name").build();
        //_customerID
        //This is done by getting the extra data from the intent
        _customerID = getIntent().getIntExtra("ID", _customerID);
        //_drinkOrderRecycler
        _drinkOrderRecycler = new DrinkOrderRecyler();

        //Creates a new Recycler view and sets the LinearLayout to the OrderRecyler
        RecyclerView recyclerView = findViewById(R.id.OrderRecyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //sets the adapter to _drinkOrderRecycler
        recyclerView.setAdapter(_drinkOrderRecycler);
        // sets _drinkOrderRecycler customerId to _customerID
        _drinkOrderRecycler.setCustomerID(_customerID);
    }

    /**
     * A method called when this is resumed
     */
    public void onResume()
    {
        super.onResume();
        //Gets all the data from the database DAO
        LiveData<List<Drink>> drinks = _db.drinksDAO().getAll();
        //Observes the changes to the database
        drinks.observe(this, new Observer<List<Drink>>() {
            @Override
            public void onChanged(List<Drink> drinkList) {
                //calls setDrinkList
                _drinkOrderRecycler.setDrinkList(_db, drinkList);
            }
        });
        //Gets all the data from the database DAO
        LiveData<List<DrinkOrder>> orders = _orderDB._orderDAO().getAll();
        //Observes the changes to the database
        orders.observe(this, new Observer<List<DrinkOrder>>() {
            @Override
            public void onChanged(List<DrinkOrder> drinkOrders) {
                //calls setOrderList
                _drinkOrderRecycler.setOrderList(_orderDB, drinkOrders);
            }
        });
    }
}