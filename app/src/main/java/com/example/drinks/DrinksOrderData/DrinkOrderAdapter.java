package com.example.drinks.DrinksOrderData;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.drinks.DrinkData.Drink;
import com.example.drinks.DrinkData.DrinkDB;
import com.example.drinks.R;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *  A class which Displays all the orders a customer has ordered
 */
public class DrinkOrderAdapter extends RecyclerView.Adapter<DrinkOrderAdapter.ViewHolder> {
    //A DrinksDB called _db
    private DrinkDB _db;
    //A List of Drink called _drinkList
    private List<Drink> _drinkList;

    //A OrderDB called _orderDB
    private OrderDB _orderDB;
    //A List of DrinkOrder called _orderList
    private List<DrinkOrder> _orderList;
    //A int called _currentCustomerID
    private int _currentCustomerID;

    /**
     * This method is used to Inflate the layout to fit the
     * parent's context and will be displayed on a view
     *
     * @param parent The parent so that the view is fit to the context
     * @param viewType
     * @return a new ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Creates a new LayoutInflater called layoutInflater from the parents context
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //Creates a new View using the layoutInflater
        View view = layoutInflater.inflate(R.layout.activity_add_drink_order, parent, false);
        //Returns a new ViewHolder using view
        return (new ViewHolder(view));
    }

    /**
     * A method that sets the current customerID
     * @param id the id the current customer is set to
     */
    public void setCustomerID(int id)
    {
        _currentCustomerID = id;
    }

    /**
     * This method binds the ViewHolder to the data
     *
     * @param holder the ViewHolder the data is being bound to
     * @param position the position of the data in the database / list
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Gets the current Drink from within the list
        final Drink drink = _drinkList.get(position);

        //Sets the views in the Holder to data from drink
        holder._drinkTitle.setText(drink._title);
        holder._descText.setText(drink._description);
        holder._priceText.setText(Float.toString(drink._price));

        holder._addButton.setOnClickListener(view ->
        {
            onClick(drink, holder._context);
        });
    }

    /**
     * A method that responds to a click
     * @param drink the drink that is selected
     * @param context the activity context
     */
    private void onClick(Drink drink, Context context)
    {
        //Creates a new order and intialises it with the drink details
        //and customerID
        final DrinkOrder order = new DrinkOrder();
        order._customerID = _currentCustomerID;
        order._drinkPrice = drink._price;
        order._drinkName = drink._title;

        //Creates a new executor called executor
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            /**
             * A method used to run the executor that inserts a order into the OrderDB
             */
            @Override
            public void run() {
                _orderDB._orderDAO().insert(order);
            }
        });
        //finishes the executor
        ((Activity)context).finish();
    }

    /**
     * A method used to get the amount of items in the customer list
     * @return an int which is the amount of items
     */
    @Override
    public int getItemCount() {
        //if the list is null return 0
        if(_drinkList == null) return 0;
        //returns the list size
        return _drinkList.size();
    }
    /**
     * A Method used to set the drinkList in the class
     * @param db The orderDB that is used to set
     * @param drinks The drinkList that is used to set
     */
    public void setDrinkList(DrinkDB db, List<Drink> drinks)
    {
        //INITIALISE class variables
        //_db
        this._db = db;
        //_drinkList
        this._drinkList = drinks;
        //Notifies  the data has changed
        notifyDataSetChanged();
    }

    /**
     * A Method used to set the orderList in the class
     * @param db The orderDB that is used to set
     * @param orders The orderList that is used to set
     */
    public void setOrderList(OrderDB db, List<DrinkOrder> orders)
    {
        //INITIALISE class variables
        //_orderDB
        this._orderDB = db;
        //_orderList
        this._orderList = orders;
        //Notifies  the data has changed
        notifyDataSetChanged();
    }
    /**
     * A class which holds individual views inside the Recycler
     */
    public class ViewHolder extends  RecyclerView.ViewHolder
    {
        //TextViews called _drinkTitle, _descText and _priceText
        TextView _drinkTitle, _descText, _priceText;
        //A Button called _addButton
        Button _addButton;
        //A Context called _context
        Context _context;

        /**
         * The recommended constructor for ViewHolder
         * @param itemView the item it is holding
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //INITIALISE CLASS VARIABLES
            //_drinkTitle
            _drinkTitle = itemView.findViewById(R.id.OrderTitle_Text);
            //_descText
            _descText = itemView.findViewById(R.id.OrderDesc_Text);
            //_priceText
            _priceText = itemView.findViewById(R.id.OrderPrice_Text);
            //_addButton
            _addButton = itemView.findViewById(R.id.AddOrder_Button);
            //_context
            _context = itemView.getContext();
        }

    }
}