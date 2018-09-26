/*
Based off the work by Ravi Tamada at https://www.androidhive.info/2016/05/android-working-with-card-view-and-recycler-view/
18/05/16
 */

package com.example.noahburghardt.burghard_feelsbook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FeelingsAdapter extends RecyclerView.Adapter<FeelingsAdapter.MyViewHolder> {
    private FeelingList feelings;
    private Context context;
    private Map<String, Integer> icons;

    // holder for emotion card views
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView card_icon;
        public TextView card_title, card_date, card_comment;

        public MyViewHolder(View view) {
            super(view);
            this.card_icon = view.findViewById(R.id.card_icon);
            this.card_title = view.findViewById(R.id.card_title);
            this.card_date = view.findViewById(R.id.card_date);
            this.card_comment = view.findViewById(R.id.card_comment);
        }
    }

    public FeelingsAdapter(Context mContext, FeelingList feelings) {
        this.context = mContext;
        this.feelings = feelings;
        this.icons = new HashMap<>();
        this.icons.put("anger", R.drawable.ic_anger_icon);
        this.icons.put("sadness", R.drawable.ic_sadness_icon);
        this.icons.put("joy", R.drawable.ic_joy_icon);
        this.icons.put("fear", R.drawable.ic_fear_icon);
        this.icons.put("love", R.drawable.ic_love_icon);
        this.icons.put("surprise", R.drawable.ic_surprise_icon);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mood_card, parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Feeling feeling = feelings.getFeeling(position);
        holder.card_title.setText(feeling.getEmotion());
        holder.card_date.setText(feeling.getDate());
        holder.card_comment.setText(feeling.getComment());
        holder.card_icon.setImageDrawable(this.context.getDrawable(this.icons.get(feeling.getEmotion())));
        // load icon matching with emotion

        //Glide.with(this.context).load(R.drawable.ic_anger_icon).into(holder.card_icon);

    }

    @Override
    public int getItemCount(){
        return feelings.size();
    }
}
