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

    public Holder(View itemView) {
        super(itemView);
        this.gameText = itemView.findViewById(R.id.gameText);
        this.context = itemView.getContext();

        itemView.setOnClickListener(this);

        games = new ArrayList<>();
        games.addAll(gameInstructionsActivity.getGames());
    }

    @Override
    public void onClick(View v) {
        final Intent intent;
        intent = new Intent(context, GameViewActivity.class);
        intent.putExtra("Game", games.get(getAdapterPosition()).getGame());
        intent.putExtra("Description", games.get(getAdapterPosition()).getDescription());
        context.startActivity(intent);
    }

    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }
}
