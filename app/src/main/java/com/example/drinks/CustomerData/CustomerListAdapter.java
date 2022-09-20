package com.example.drinks.CustomerData;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drinks.CustomerOrder;
import com.example.drinks.DrinksOrderData.AddDrinkOrderViewer;
import com.example.drinks.DrinksOrderData.DrinkOrder;
import com.example.drinks.DrinksOrderData.OrderDB;
import com.example.drinks.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 *  CustomerListAdapter is a class which used the RecyclerView class to display all of the Customers
 *  and the total amount that customer has spent when viewing
 */
public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> {

    //A list of customers called _customerList
    private List<Customer> _customerList;
    //The CustomerDB called _customerDB
    private CustomerDB _customerDB;

    // The OrderDB called _orderDB
    private OrderDB _orderDB;
    //A list of Drink orders called _orderList
    private List<DrinkOrder> _orderList;


    /**
     * This method is called whe the ViewHolder is created and is used to Inflate the layout to fit the
     * parent's context and will be displayed on a view
     *
     * @param parent The parent so that the view is fit to the context
     * @param viewType
     * @return a new ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //Creates a new LayoutInflater called layoutInflater from the parents context
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //Creates a new View using the layoutInflater
        View view = layoutInflater.inflate(R.layout.activity_customer_list_viewer, parent, false);
        //Returns a new ViewHolder using view
        return (new ViewHolder(view));
    }

    /**
     * This method binds the ViewHolder to the data
     *
     * @param holder the ViewHolder the data is being bound to
     * @param position the position of the data in the database / list
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Gets a customer from the current list position
        final Customer customer = _customerList.get(position);
        //INTIALSES a float called price
        float price = 0;
        //Loops over each item in _orderList
        for(DrinkOrder order : _orderList)
        {
            //If the orders ID is the same as the Customers ID this is true
            if(order._customerID == customer._uid)
            {
                //Adds the price of the drink to the price that will be displayed as a total
                price += order._drinkPrice;
            }
        }

        //Binds the data to the Different aspects of the view
        holder._customerSeating.setText(customer._location);
        holder._customerName.setText(customer._name);
        holder._priceText.setText(Float.toString(price));
        //Binds a Click for the buttons on the view
        holder._editButton.setOnClickListener(view ->
        {
            onEditClickButton(holder._context, customer._uid);
        });
        holder._viewButton.setOnClickListener(view ->
        {
            onViewClick(holder._context, customer._uid);
        });
    }

    /**
     * A method that responds when the "View" button is clicked
     *
     * @param context The context of the holder used to start an intent
     * @param customerID the current customers ID
     */
    public void onViewClick(Context context, int customerID)
    {
        //INTIALSE a new intent using the context and the class which is being started
        Intent i = new Intent(context, CustomerOrder.class);
        //Sends extra data to the intent
        //The data sent is the customerID
        i.putExtra("ID", customerID);
        //Starts the intent
        context.startActivity(i);
    }
    /**
     * A method that responds when the "Edit" button is clicked
     *
     * @param context The context of the holder used to start an intent
     * @param customerID the current customers ID
     */
    public void onEditClickButton(Context context, int customerID)
    {
        //INTIALSE a new intent using the context and the class which is being started
        Intent i = new Intent(context, AddDrinkOrderViewer.class);
        //Sends extra data to the intent
        //The data sent is the customerID
        i.putExtra("ID", customerID);
        //Starts the intent
        context.startActivity(i);
    }

    /**
     * A method used to get the amount of items in the customer list
     * @return an int which is the amount of items
     */
    @Override
    public int getItemCount() {
        //if the list is null returns 0
        if(_customerList == null) return 0;
        //Returns the size of the list
        return _customerList.size();
    }

    /**
     * A Method used to set the orderList and OrderDB in the class
     * @param db The orderDB that is used to set
     * @param orderList The orderList that is used to set
     */
    public void setOrderList(OrderDB db,List<DrinkOrder> orderList) {
        //INITIALISE class variables
        //_orderDB
        this._orderDB = db;
        //_orderList
        this._orderList = orderList;
        //Notifies the data has been changed
        notifyDataSetChanged();
    }
    /**
     * A Method used to set the customerList and CustomerDB in the class
     * @param db The CustomerDB that is used to set
     * @param customers The customerList that is used to set
     */
    public void setCustomerList(CustomerDB db, List<Customer> customers) {
        //INITIALISE class variables
        //_customerDB
        this._customerDB = db;
        //_customerList
        this._customerList = customers;
        //Notifies the data has been changed
        notifyDataSetChanged();
    }

    /**
     * A Class which stores the individual views insider the Recycler
     */
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        // A text View that stores the customer name within the view
        //Customer seat location within the view
        // Price within the view
        TextView _customerName, _customerSeating, _priceText;
        // A FloatingActionButton which stores the
        //EditButton as _editButton
        //ViewButton as _viewButton
        FloatingActionButton _editButton, _viewButton;
        //A context stored as _context
        Context _context;

        /**
         * The Recommended constructor
         * @param itemView the View that the class variables are intialised from
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //INITIALISE class variables
            //_context
            _context = itemView.getContext();
            //_customerSeating
            _customerSeating = itemView.findViewById(R.id.SeatLocon_Text);
            //_customerName
            _customerName = itemView.findViewById(R.id.CustomerSeat_Text);
            //_editButton
            _editButton = itemView.findViewById(R.id.editCustomerOrder_Button);
            //_priceText
            _priceText = itemView.findViewById(R.id.TotalAmount_Text);
            //_viewButton
            _viewButton = itemView.findViewById(R.id.ViewCustomerDrinks_Button);

        }
    }
}
