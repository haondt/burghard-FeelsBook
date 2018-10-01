/* Back arrow toolbar by CodingDemos
    https://www.codingdemos.com/android-toolbar-back-button/
 */

package com.example.noahburghardt.burghard_feelsbook;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EditCardActivity extends AppCompatActivity {

    public ImageView edit_icon;
    public TextView edit_title, edit_date, edit_comment;
    public CoordinatorLayout layout;
    public LinearLayout date_picker, time_picker;
    private Feeling feeling;
    private FeelingList feelings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Edit Mood");
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow);
        setSupportActionBar(toolbar);

        // Add back button to toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Fetch intent and passed feeling object
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        // fetch singleton feeling list
        this.feelings = new FeelingListController().getFeelingList();
        this.feeling = this.feelings.getFeeling(position);

        // Fetch view IDs
        this.edit_comment = findViewById(R.id.edit_comment);
        this.edit_icon = findViewById(R.id.edit_icon);
        this.edit_title = findViewById(R.id.edit_title);
        this.edit_date = findViewById(R.id.edit_date);
        this.layout = findViewById(R.id.edit_mood_layout);
        this.date_picker = findViewById(R.id.date_layout);
        this.time_picker = findViewById(R.id.time_layout);

        // Populate view IDs
        this.edit_title.setText(feeling.getTitle());
        this.edit_comment.setText(feeling.getComment());
        this.edit_date.setText(feeling.getDate8601());

        Resources resources = getResources();
        final int resourceID = resources.getIdentifier("ic_" + feeling.getEmotion() + "_icon", "drawable",getPackageName());
        this.edit_icon.setImageDrawable(getResources().getDrawable(resourceID));



    }

    // Date picker by Vasily Kabunov
    // https://stackoverflow.com/questions/2055509/datetime-picker-in-android-application
    // tapping the time stamp opens up editor
    public void onTimeClick(View v){
        this.date_picker.setVisibility(LinearLayout.VISIBLE);
    }

    // confirming date opens up time
    public void onConfirmDate(View v){
        this.date_picker.setVisibility(LinearLayout.GONE);
        this.time_picker.setVisibility(LinearLayout.VISIBLE);
    }

    // confirming time updates the timestamp
    public void onConfirmTime(View v){
        this.time_picker.setVisibility(LinearLayout.GONE);
        // fetch selected date
        DatePicker datePicker = (DatePicker) findViewById(R.id.date_picker);
        TimePicker timePicker = (TimePicker) findViewById(R.id.time_picker);
        Calendar calendar = new GregorianCalendar(datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth(),
                timePicker.getCurrentHour(),
                timePicker.getCurrentMinute());
        // update feeling object, timestamp visual and save data
        this.feeling.setDate(calendar.getTime());
        this.edit_date.setText(this.feeling.getDate8601());
        this.feelings.save(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
    }

    // hitting cancel on either step does nothing
    public void onCancelClick(View v){
        this.date_picker.setVisibility(LinearLayout.GONE);
        this.time_picker.setVisibility(LinearLayout.GONE);
    }

    @Override
    protected void onPause(){
        super.onPause();
        this.feeling.setComment(this.edit_comment.getText().toString());
        this.feelings.save(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
    }

    @Override
    protected void onStop(){
        super.onStop();
        this.feeling.setComment(this.edit_comment.getText().toString());
        this.feelings.save(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
    }

}
