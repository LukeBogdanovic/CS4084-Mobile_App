package com.ul.mobileappproject;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final Context context;
    TextView gameText;
    ItemClickListener itemClickListener;
    GameInstructionsActivity gameInstructionsActivity = new GameInstructionsActivity();
    ArrayList<Game> games;

    /**
     * Holder constructor.
     * Initializes the holder userInterface.
     * Sets the on click listener for the holder.
     * Initializes the game ArrayList.
     *
     * @param itemView
     */
    public Holder(View itemView) {
        super(itemView);
        this.gameText = itemView.findViewById(R.id.gameText);
        this.context = itemView.getContext();

        itemView.setOnClickListener(this);

        games = new ArrayList<>();
        games.addAll(gameInstructionsActivity.getGames());
    }

    /**
     * Starts the PostViewActivity on click.
     * Sends the game name and game description from games ArrayList at specified position
     * to display the full post in the postView activity.
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        final Intent intent = new Intent(context, GameViewActivity.class);
        intent.putExtra("Game", games.get(getAdapterPosition()).getGame());
        intent.putExtra("Description", games.get(getAdapterPosition()).getDescription());
        context.startActivity(intent);
    }

    /**
     * Sets the itemClickListener of the holder
     *
     * @param ic
     */
    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }
}
