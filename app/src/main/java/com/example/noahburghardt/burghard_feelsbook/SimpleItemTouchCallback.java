/* Work by Aritra Roy
via https://stackoverflow.com/a/42793834
 */
package com.example.noahburghardt.burghard_feelsbook;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Adapter;
import android.widget.Toast;

public class SimpleItemTouchCallback extends ItemTouchHelper.SimpleCallback {

    private Context context;
    private FeelingsAdapter adapter;
    private FeelingList feelings;

    public SimpleItemTouchCallback(Context context, FeelingsAdapter adapter, FeelingList feelings){
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.context = context;
        this.adapter = adapter;
        this.feelings = feelings;

    }
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Toast.makeText(this.context, "on Move", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
        //Remove swiped item from list and notify the RecyclerView
        final int position = viewHolder.getAdapterPosition();
        //Toast.makeText(context, this.adapter.getItemCount(), Toast.LENGTH_SHORT).show();
        // This is dumb
        // TODO: fix this
        this.feelings.removeFeeling(this.feelings.getFeeling(position));

        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position-1, this.feelings.size());
        this.feelings.save(PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()));
        Toast.makeText(context,"Card deleted",Toast.LENGTH_SHORT).show();
    }
}
