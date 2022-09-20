package com.example.drinks.DrinkData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drinks.R;

import java.util.List;

/**
 * DrinksListAdapter is a class which uses Recycler View to display all of the drinks in the database
 */
public class DrinkListAdapter extends RecyclerView.Adapter<DrinkListAdapter.ViewHolder> {
    //A list of drinks called _drinksList
    private List<Drink> _drinksList;
    //A DrinksDB called _db
    private DrinkDB _db;

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
        View view = layoutInflater.inflate(R.layout.activity_drink_list_viewer, parent, false);
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
    public void onBindViewHolder(@NonNull DrinkListAdapter.ViewHolder holder, int position) {
        //Gets the current drink from the list
        final Drink drink = _drinksList.get(position);
        //binds the data of the different aspects of the View to the drink
        holder._drinkTitle.setText(drink._title);
        holder._descText.setText(drink._description);
        holder._priceText.setText(Float.toString(drink._price));
    }

    /**
     * A method used to set the list of the db and drinks list
     * @param db the database that is being stored
     * @param drinks the list that is being stored
     */
    public void setDrinksList(DrinkDB db , List<Drink> drinks)
    {
        //INITIALISE CLASS variables
        //_db
        this._db = db;
        //_drinksList
        this._drinksList = drinks;
        //Notifies that the data is changed
        notifyDataSetChanged();
    }
    /**
     * A method used to get the amount of items in the drink list
     * @return an int which is the amount of items
     */
    @Override
    public int getItemCount() {
        // if the drinks list is null return 0
        if(_drinksList == null) return 0;
        //Returns the size of the drinks list
        return _drinksList.size();
    }

    /**
     * A class which stores the individual views inside the recycler
     */
    public class ViewHolder extends  RecyclerView.ViewHolder
    {
        //A text view that stores the
        //_drinkTitle
        //_descText
        //_priceText
        TextView _drinkTitle, _descText, _priceText;


        /**
         * The Recommended constructor
         * @param itemView the View that the class variables are intialised from
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //INITIALISE CLASS variables
            //_drinkTitle
            _drinkTitle = itemView.findViewById(R.id.OrderTitle_Text);
            //_descText
            _descText = itemView.findViewById(R.id.OrderDesc_Text);
            //_priceText
            _priceText = itemView.findViewById(R.id.OrderPrice_Text);
        }

    }

}
