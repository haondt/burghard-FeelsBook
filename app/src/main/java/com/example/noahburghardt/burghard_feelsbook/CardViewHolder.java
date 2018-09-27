package com.example.noahburghardt.burghard_feelsbook;

import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import com.bumptech.glide.load.engine.Resource;

// holder for card views
public class CardViewHolder extends RecyclerView.ViewHolder {
    public ImageView card_icon;
    public TextView card_title, card_date, card_comment;
    public CardView card_view;

    public CardViewHolder(View view) {
        super(view);
        this.card_icon = view.findViewById(R.id.card_icon);
        this.card_title = view.findViewById(R.id.card_title);
        this.card_date = view.findViewById(R.id.card_date);
        this.card_comment = view.findViewById(R.id.card_comment);
        this.card_view = view.findViewById(R.id.moodCardView);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), view.getTag().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void bindData(Feeling feeling, Context context){
        this.card_title.setText(feeling.getEmotion());
        this.card_date.setText(feeling.getDateString());
        this.card_comment.setText(feeling.getComment());
        Resources resources = context.getResources();
        final int resourceID = resources.getIdentifier("ic_" + feeling.getEmotion() + "_icon", "drawable",context.getPackageName());
        this.card_icon.setImageDrawable(context.getResources().getDrawable(resourceID));
    }



}
