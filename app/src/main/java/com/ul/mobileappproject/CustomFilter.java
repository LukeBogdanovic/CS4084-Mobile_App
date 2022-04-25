package com.ul.mobileappproject;

import android.widget.Filter;

import java.util.ArrayList;

public class CustomFilter extends Filter {

    ArrayList<Game> filterList;
    Adapter adapter;

    /**
     * Constructor for the custom Filter.
     * Takes in the ArrayList to be filtered and the adapter.
     * @param filterList
     * @param adapter
     */
    public CustomFilter(ArrayList<Game> filterList, Adapter adapter) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    /**
     * Filters the ArrayList by the character sequence entered by the user.
     * Returns all the resulting matches.
     * @param constraint
     * @return FilterResults
     */
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toUpperCase();
            ArrayList<Game> filteredGames = new ArrayList<>();
            for (int i = 0; i < filterList.size(); i++) {
                if(filterList.get(i).getGame().toUpperCase().contains(constraint)) {
                    filteredGames.add(filterList.get(i));
                }
            }
            results.count = filteredGames.size();
            results.values = filteredGames;
        } else {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    /**
     * Updates the User Interface with the results from the filtering of the ArrayList
     * @param constraint
     * @param results
     */
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.games = (ArrayList<Game>) results.values;
        adapter.notifyDataSetChanged();
    }
}
