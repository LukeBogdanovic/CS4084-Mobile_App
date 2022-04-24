package com.ul.mobileappproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardHolder> {

    Context ctx;
    ArrayList<Post> posts;

    public DashboardAdapter(Context ctx, ArrayList<Post> posts) {
        this.ctx = ctx;
        this.posts = posts;
    }

    @NonNull
    @Override
    public DashboardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.postmodel, null);
        DashboardHolder holder = new DashboardHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(DashboardHolder holder, int position) {
        holder.profileText.setText(posts.get(position).getProfile());
        holder.postText.setText(posts.get(position).getPostText());
        Picasso.get().load(posts.get(position).getImageURL()).into(holder.image);
        holder.setItemClickListener((view, pos) -> Snackbar.make(view, posts.get(pos).getProfile(), Snackbar.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

}
