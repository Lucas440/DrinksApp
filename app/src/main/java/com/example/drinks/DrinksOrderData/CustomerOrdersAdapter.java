package com.example.drinks.DrinksOrderData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drinks.CustomerData.CustomerListAdapter;
import com.example.drinks.DrinkData.Drink;
import com.example.drinks.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CustomerOrdersAdapter extends RecyclerView.Adapter<CustomerOrdersAdapter.ViewHolder> {

    private List<DrinkOrder> orderList;
    private OrderDB db;

    private int customerID;

    @NonNull
    @Override
    public CustomerOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.activity_customer_order_list, parent, false);

        return (new CustomerOrdersAdapter.ViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerOrdersAdapter.ViewHolder holder, int position) {
        final DrinkOrder order = orderList.get(position);
        holder.DrinkTitle.setText(order.DrinkName);
        holder.PriceText.setText((Float.toString(order.DrinkPrice)));

    }

    public void setCustomerID(int id)
    {
        customerID = id;
    }

    @Override
    public int getItemCount() {
        if(orderList == null) return 0;
        return orderList.size();
    }

    public void setOrderList(OrderDB db, List<DrinkOrder> orders)
    {
        this.db = db;
        this.orderList = orders;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView DrinkTitle;
        TextView PriceText;
        Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();
            DrinkTitle = itemView.findViewById(R.id.OrderTitle_Text);
            PriceText = itemView.findViewById(R.id.OrderPrice_Text);


        }
    }
}
