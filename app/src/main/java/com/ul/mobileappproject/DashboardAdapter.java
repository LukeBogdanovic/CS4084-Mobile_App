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

    /**
     * Adapter constructor for the dashboard Activity.
     * Initializes the posts ArrayList with the ArrayList from the Dashboard
     * Activity.
     *
     * @param ctx
     * @param posts
     */
    public DashboardAdapter(Context ctx, ArrayList<Post> posts) {
        this.ctx = ctx;
        this.posts = posts;
    }

    /**
     * Creates the holder object using the associated layout xml file.
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public DashboardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.postmodel, null);
        DashboardHolder holder = new DashboardHolder(v);
        return holder;
    }

    /**
     * Sets the profile and post text of the holder.
     * Sets the image of the holder using an image loaded in from a URL.
     * Creates a listener to create feedback for the user when they click on a holder item.
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(DashboardHolder holder, int position) {
        holder.profileText.setText(posts.get(position).getProfile());
        holder.postText.setText(posts.get(position).getPostText());
        Picasso.get().load(posts.get(position).getImageURL()).into(holder.image);
        holder.setItemClickListener((view, pos) -> Snackbar.make(view, posts.get(pos).getProfile(), Snackbar.LENGTH_SHORT).show());
    }

    /**
     * Gets the number of items in the posts ArrayList
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return posts.size();
    }

}
