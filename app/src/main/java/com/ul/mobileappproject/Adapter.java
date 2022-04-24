package com.ul.mobileappproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Holder> implements Filterable {

    Context ctx;
    ArrayList<Game> games, filterList;
    CustomFilter filter;

    public Adapter(Context ctx, ArrayList<Game> games) {
        this.ctx = ctx;
        this.games = games;
        this.filterList = games;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model, null);
        Holder holder = new Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.gameText.setText(games.get(position).getGame());
        holder.setItemClickListener((view, pos) -> Snackbar.make(view, games.get(pos).getGame(), Snackbar.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    @Override
    public Filter getFilter() {
        if(filter == null) {
            filter  = new CustomFilter(filterList,this);
        }
        return filter;
    }

}
