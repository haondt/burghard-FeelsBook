/*
FeelsBook: Quickly record, edit and view your emotions.

Copyright (C) 2018 Noah Burghardt burghard@ualberta.ca

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

 */

/*
    saved/load feeling built off the work my Mkyong
    https://www.mkyong.com/java/how-do-convert-java-object-to-from-json-format-gson-api/
 */
package com.example.noahburghardt.burghard_feelsbook;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.preference.PreferenceManager;
import android.renderscript.Type;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private RecyclerView moodHistory;
    private FeelingList feelings;
    private FeelingsAdapter adapter;
    public SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize attributes
        this.feelings = new FeelingList();
        this.moodHistory = findViewById(R.id.MoodHistory);
        this.adapter = new FeelingsAdapter(this, this.feelings);

        // use grid layout manager
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        moodHistory.setLayoutManager(mLayoutManager);

        // set decoration, animation and adapter
        moodHistory.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        moodHistory.setItemAnimator(new DefaultItemAnimator());
        moodHistory.setAdapter(this.adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SimpleItemTouchCallback(this, this.adapter, this.feelings));
        itemTouchHelper.attachToRecyclerView(moodHistory);

        // load data
        // From the shared preferences tutorial
        // https://developer.android.com/training/data-storage/shared-preferences
        // and ρяσѕρєя K
        // https://stackoverflow.com/a/11316855
        this.sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        // numFeelings: number of saved feelings
        // savedFeelings: an [emotion],[date] formatted string for each feeling
        // savedComments: a comment for each saved feeling
        Gson gson = new Gson();
        String json = this.sharedPref.getString("savedFeelings", "");
        FeelingList newFeelings = gson.fromJson(json, FeelingList.class);

        // Add saved feelings to new feelingList
        if (newFeelings != null){
            for(Feeling feeling : newFeelings.getFeelings()){
                this.feelings.addFeeling(feeling);
            }
        }


    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     * - credit Ravi Tamada 2018-07-12
     *  https://www.androidhive.info/2016/05/android-working-with-card-view-and-recycler-view/
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }

    }


    private int dpToPx(int dp){
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    // called by each button,
    // relies on button tag for emotion type
    public void addFeeling(View v){
        // add button
        Feeling feeling = new Feeling(v.getTag().toString());
        this.feelings.addFeeling(feeling);
        // scroll and update cards
        int pos = this.feelings.getFeelingPosition(feeling);
        this.moodHistory.smoothScrollToPosition(pos);
        this.adapter.notifyItemInserted(pos);
        this.adapter.notifyItemRangeChanged(pos, this.feelings.size());

        // update saved feelings
        this.feelings.save(this.sharedPref);


    }

    @Override
    protected void onResume(){
        super.onResume();
        this.adapter.notifyDataSetChanged();

    }


}
