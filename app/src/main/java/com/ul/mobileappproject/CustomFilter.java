package com.ul.mobileappproject;

import android.widget.Filter;

import java.util.ArrayList;

public class CustomFilter extends Filter {

    ArrayList<Game> filterList;
    Adapter adapter;

    public CustomFilter(ArrayList<Game> filterList, Adapter adapter) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

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

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.games = (ArrayList<Game>) results.values;
        adapter.notifyDataSetChanged();
    }
}
