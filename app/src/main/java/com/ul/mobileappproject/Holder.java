package com.ul.mobileappproject;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView gameText,descriptionText;

    ItemClickListener itemClickListener;

    public Holder(View itemView) {
        super(itemView);
        this.gameText = itemView.findViewById(R.id.gameText);
        this.descriptionText = itemView.findViewById(R.id.description_text);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }
}
