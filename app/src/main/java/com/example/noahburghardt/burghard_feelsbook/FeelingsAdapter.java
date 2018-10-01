/*
Based off the work by Ravi Tamada at
https://www.androidhive.info/2016/05/android-working-with-card-view-and-recycler-view/
 */

package com.example.noahburghardt.burghard_feelsbook;

import android.content.Context;
import android.content.Intent;
import android.support.v13.view.DragStartHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    public void onBindViewHolder(final CardViewHolder holder, final int position) {
        // grad corresponding Feeling object
        final Feeling feeling = feelings.getFeeling(position);
        // bind data
        holder.bindData(feeling, context);

        // Open up edit screen when cards clicked
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent container = new Intent(context.getApplicationContext(), EditCardActivity.class);
                container.putExtra("position", position);
                context.startActivity(container);

            }
        });


    }

    // return size of collection
    @Override
    public int getItemCount(){
        return feelings.size();
    }

}
