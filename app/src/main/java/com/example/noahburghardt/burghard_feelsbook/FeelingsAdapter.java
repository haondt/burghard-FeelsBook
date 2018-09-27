/*
Based off the work by Ravi Tamada at
https://www.androidhive.info/2016/05/android-working-with-card-view-and-recycler-view/
18/05/16
 */

package com.example.noahburghardt.burghard_feelsbook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

public class FeelingsAdapter extends RecyclerView.Adapter<CardViewHolder> {
    private FeelingList feelings;
    final private Context context;
    private Map<String, Integer> icons;

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

    // called on each initialization of cards
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mood_card, parent,false);
        return new CardViewHolder(itemView);
    }

    // pass data from Feeling in feelingList model to corresponding card at given position
    @Override
    public void onBindViewHolder(final CardViewHolder holder, int position) {
        holder.bindData(feelings.getFeeling(position), context);



    }

    // return size of collection
    @Override
    public int getItemCount(){
        return feelings.size();
    }
}
