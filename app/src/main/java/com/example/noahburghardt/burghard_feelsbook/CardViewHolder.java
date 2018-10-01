package com.example.noahburghardt.burghard_feelsbook;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v13.view.DragStartHelper;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;


// holder for card views
public class CardViewHolder extends RecyclerView.ViewHolder {


    public ImageView card_icon;
    public TextView card_title, card_date;
    public CardView card_view;

    public CardViewHolder(View view) {
        super(view);
        this.card_icon = view.findViewById(R.id.card_icon);
        this.card_title = view.findViewById(R.id.card_title);
        this.card_date = view.findViewById(R.id.card_date);
        this.card_view = view.findViewById(R.id.moodCardView);

    }

    public void bindData(Feeling feeling, Context context){
        this.card_title.setText(feeling.getTitle());
        this.card_date.setText(feeling.getDate8601());
        Resources resources = context.getResources();
        final int resourceID = resources.getIdentifier("ic_" + feeling.getEmotion() + "_icon", "drawable",context.getPackageName());
        this.card_icon.setImageDrawable(context.getResources().getDrawable(resourceID));
    }



}
