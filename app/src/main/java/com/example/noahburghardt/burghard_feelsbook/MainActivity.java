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
    saved/load feeling built off the work by Mkyong
    https://www.mkyong.com/java/how-do-convert-java-object-to-from-json-format-gson-api/
 */
package com.example.noahburghardt.burghard_feelsbook;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.preference.PreferenceManager;
import android.renderscript.Type;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static android.support.v4.view.MenuItemCompat.getActionView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView moodHistory;
    private FeelingList feelings;
    private FeelingsAdapter adapter;
    public SharedPreferences sharedPref;
    private HashMap<String, TextView> nav_counters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // draw button on toolbar
        // via the android developers tutorial
        // https://developer.android.com/training/implementing-navigation/nav-drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle("FeelsBook");
        toolbar.setNavigationIcon(R.drawable.ic_menu_white);
        setSupportActionBar(toolbar);

        // attach appbar button to drawer toggle
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        // Nav drawer counter implementation by Hari Vignesh Jayapalan
        // https://android.jlelse.eu/android-adding-badge-or-count-to-the-navigation-drawer-84c93af1f4d9
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        this.nav_counters = new HashMap<String, TextView>();
        nav_counters.put("anger", (TextView) navigationView.getMenu().findItem(R.id.nav_anger).getActionView());
        nav_counters.put("sadness", (TextView) navigationView.getMenu().findItem(R.id.nav_sadness).getActionView());
        nav_counters.put("joy", (TextView) navigationView.getMenu().findItem(R.id.nav_joy).getActionView());
        nav_counters.put("fear", (TextView) navigationView.getMenu().findItem(R.id.nav_fear).getActionView());
        nav_counters.put("love", (TextView) navigationView.getMenu().findItem(R.id.nav_love).getActionView());
        nav_counters.put("surprise", (TextView) navigationView.getMenu().findItem(R.id.nav_surprise).getActionView());

        // initialize attributes
        this.feelings = new FeelingListController().getFeelingList();
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
        Gson gson = new Gson();
        String json = this.sharedPref.getString("savedFeelings", "");
        FeelingList newFeelings = gson.fromJson(json, FeelingList.class);

        // Add saved feelings to new feelingList
        if (newFeelings != null){
            for(Feeling feeling : newFeelings.getFeelings()){
                this.feelings.addFeeling(feeling);
            }
        }

        // fill in count drawer
        initializeCountDrawer();

    }

    public void initializeCountDrawer(){
        String[] emotions = {"anger", "fear", "joy", "love", "sadness", "surprise"};
        for(String s : emotions){
            TextView view = this.nav_counters.get(s);
            view.setText(Integer.toString(this.feelings.size(s)));
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

        // update drawer counter
        initializeCountDrawer();


    }

    @Override
    protected void onResume(){
        super.onResume();
        this.adapter.notifyDataSetChanged();
        // update drawer counter
        this.initializeCountDrawer();

    }


}
