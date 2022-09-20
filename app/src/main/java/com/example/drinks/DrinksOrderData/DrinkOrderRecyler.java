package com.example.drinks.DrinksOrderData;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
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

public class DrinkOrderRecyler extends RecyclerView.Adapter<DrinkOrderRecyler.ViewHolder> {

    private DrinkDB db;
    private List<Drink> drinkList;

    private OrderDB orderDB;
    private List<DrinkOrder> orderList;

    private int currentCustomerID;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.activity_add_drink_order, parent, false);

        return (new ViewHolder(view));
    }


    public void setCustomerID(int id)
    {
        currentCustomerID = id;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Drink drink = drinkList.get(position);

        holder.DrinkTitle.setText(drink._title);
        holder.DescText.setText(drink._description);
        holder.PriceText.setText(Float.toString(drink._price));

        holder.AddButton.setOnClickListener(view ->
        {
            onClick(drink, holder.context);
        });
    }

    private void onClick(Drink drink, Context context)
    {
        final DrinkOrder order = new DrinkOrder();

        order._customerID = currentCustomerID;
        order._drinkPrice = drink._price;
        order._drinkName = drink._title;
        LiveData<List<DrinkOrder>> orders = orderDB._orderDAO().getAll();

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                orderDB._orderDAO().insert(order);
            }
        });

        ((Activity)context).finish();
    }

    @Override
    public int getItemCount() {
        if(drinkList == null) return 0;
        return drinkList.size();
    }

    public void setDrinkList(DrinkDB db, List<Drink> orders)
    {
        this.db = db;
        this.drinkList = orders;
        notifyDataSetChanged();
    }

    public void setOrderList(OrderDB db, List<DrinkOrder> orders)
    {
        this.orderDB = db;
        this.orderList = orders;
        notifyDataSetChanged();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder
    {
        TextView DrinkTitle;
        TextView DescText;
        TextView PriceText;
        Button AddButton;

        Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            DrinkTitle = itemView.findViewById(R.id.OrderTitle_Text);
            DescText = itemView.findViewById(R.id.OrderDesc_Text);
            PriceText = itemView.findViewById(R.id.OrderPrice_Text);
            AddButton = itemView.findViewById(R.id.AddOrder_Button);

            context = itemView.getContext();
        }

    }
}