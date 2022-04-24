package com.ul.mobileappproject;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DashboardHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final Context context;
    public ImageView image;
    TextView profileText, postText;
    ItemClickListener itemClickListener;
    DashboardActivity dashboardActivity = new DashboardActivity();
    ArrayList<Post> posts;

    public DashboardHolder(View itemView) {
        super(itemView);
        this.profileText = itemView.findViewById(R.id.profileText);
        this.postText = itemView.findViewById(R.id.postText);
        this.image = itemView.findViewById(R.id.postImage);
        this.context = itemView.getContext();

        itemView.setOnClickListener(this);

        posts = new ArrayList<>();
        posts.addAll(dashboardActivity.getPosts());
    }

    @Override
    public void onClick(View v) {
        final Intent intent = new Intent(context, PostViewActivity.class);
        intent.putExtra("Profile", posts.get(getAdapterPosition()).getProfile());
        intent.putExtra("PostText", posts.get(getAdapterPosition()).getPostText());
        intent.putExtra("ImageURL", posts.get(getAdapterPosition()).getImageURL());
        context.startActivity(intent);
    }

    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }


}
