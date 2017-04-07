package com.saltstudios.ftdiscovery.exeterdiningapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gordon on 13/11/16.
 */

public class CustomAdapter extends ArrayAdapter<String> {

    private ArrayList<Float> ratings;
    private ArrayList<Integer> numOfRatings;


    public CustomAdapter(Context dinner, ArrayList<String> f, ArrayList<Float> r, ArrayList<Integer> num) {
        super(dinner, R.layout.custom_row,f);
        ratings=r;
        numOfRatings=num;
    }



    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater foodInflater = LayoutInflater.from(getContext());
        View customView = foodInflater.inflate(R.layout.custom_row, parent,false);
        String singleFoodItem = getItem(position);
        TextView foodLabel = (TextView) customView.findViewById(R.id.foodLabel);
        TextView qualityHumanCount = (TextView) customView.findViewById(R.id.numOfRatings);
        final RatingBar foodBar = (RatingBar) customView.findViewById(R.id.ratingForFood);
        if(numOfRatings.size()>0) {
            String yes = String.valueOf(numOfRatings.get(position));
            qualityHumanCount.setText(yes);
            foodLabel.setText(singleFoodItem);
            foodBar.setRating(ratings.get(position));
        }
        return customView;
    }

}
