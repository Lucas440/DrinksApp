package com.example.drinks.DrinksOrderData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drinks.R;

import java.util.List;

/**
 * A class which is used to Display the customers orders
 */
public class CustomerOrdersAdapter extends RecyclerView.Adapter<CustomerOrdersAdapter.ViewHolder> {

    //A list of orders called _orderList
    private List<DrinkOrder> _orderList;
    //A OrderDB called _db
    private OrderDB _db;

    //A int called customerID
    private int _customerID;

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
    public CustomerOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //Creates a new LayoutInflater called layoutInflater from the parents context
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //Creates a new View using the layoutInflater
        View view = layoutInflater.inflate(R.layout.activity_customer_order_list, parent, false);
        //Returns a new ViewHolder using view
        return (new CustomerOrdersAdapter.ViewHolder(view));
    }
    /**
     * This method binds the ViewHolder to the data
     *
     * @param holder the ViewHolder the data is being bound to
     * @param position the position of the data in the database / list
     */
    @Override
    public void onBindViewHolder(@NonNull CustomerOrdersAdapter.ViewHolder holder, int position) {
        //Gets an order from the list in the current position
        final DrinkOrder order = _orderList.get(position);
        //Sets the text in the views to the name and the price
        holder._drinkTitle.setText(order._drinkName);
        holder._priceText.setText((Float.toString(order._drinkPrice)));

    }

    /**
     * a methhod used to set the customerID
     * @param id the customers ID
     */
    public void set_customerID(int id)
    {
        _customerID = id;
    }

    /**
     * A method used to get the amount of items in the customer list
     * @return an int which is the amount of items
     */
    @Override
    public int getItemCount() {
        //if the list is null return 0
        if(_orderList == null) return 0;
        //returns the list size
        return _orderList.size();
    }

    /**
     * A Method used to set the orderList in the class
     * @param db The orderDB that is used to set
     * @param orders The orderList that is used to set
     */
    public void setOrderList(OrderDB db, List<DrinkOrder> orders)
    {
        //INITIALISE class variables
        //_db
        this._db = db;
        //_orderList
        this._orderList = orders;
        //Notifies the data has been changed
        notifyDataSetChanged();
    }

    /**
     * A class which holds individual views inside the Recycler
     */
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        //TextViews called _drinkTitle and _priceText
        TextView _drinkTitle, _priceText;
        //A Context Variable called _context
        Context _context;

        /**
         * The Recommended constructor for ViewHolder
         * @param itemView the item it is holding
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //INITIALISE CLASS VARIABLES
            //_context
            _context = itemView.getContext();
            //_drinkTitle
            _drinkTitle = itemView.findViewById(R.id.OrderTitle_Text);
            //_priceText
            _priceText = itemView.findViewById(R.id.OrderPrice_Text);


        }
    }
}
