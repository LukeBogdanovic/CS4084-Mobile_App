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

    /**
     * Holder for a post on the Dashboard activity.
     * Initializes the User interface elements from the xml file.
     * Adds all the posts from the dashboard activity function to the posts ArrayList.
     *
     * @param itemView
     */
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

    /**
     * Starts the PostViewActivity on click.
     * Sends the profile, postText and ImageURl from posts ArrayList at specified position
     * to display the full post in the postView activity.
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        final Intent intent = new Intent(context, PostViewActivity.class);
        intent.putExtra("Profile", posts.get(getAdapterPosition()).getProfile());
        intent.putExtra("PostText", posts.get(getAdapterPosition()).getPostText());
        intent.putExtra("ImageURL", posts.get(getAdapterPosition()).getImageURL());
        context.startActivity(intent);
    }

    /**
     * Sets the itemClickListener of the holder
     * @param ic
     */
    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }


}
