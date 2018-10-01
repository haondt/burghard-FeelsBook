package com.example.noahburghardt.burghard_feelsbook;


import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

// Object for storing feelings
public  class Feeling implements Serializable {
    private String emotion;
    private Calendar calendar;
    private String comment;

    // initialize a new feeling with a given emotion
    public Feeling(String emotion){
        this.emotion = emotion;
        this.calendar = new GregorianCalendar();
        this.comment = "";
    }

    public String getEmotion(){
        return this.emotion;
    }

    // return captilized emotion
    public String getTitle(){
        return this.emotion.substring(0,1).toUpperCase() + this.emotion.substring(1);
    }

    public String getComment(){
        return this.comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public Calendar getCalendar(){
        return this.calendar;
    }

    public void setCalendar(Calendar calendar){
        this.calendar = calendar;
    }

    // allows modification of date with year, month, day, [hour, minute] or date object
    public void setDate(int year, int month, int day){
        this.calendar.set(Calendar.YEAR, year);
        this.calendar.set(Calendar.MONTH, month);
        this.calendar.set(Calendar.DAY_OF_MONTH, day);
    }

    public void setDate(int year, int month, int day, int hour, int minute){
        this.calendar.set(Calendar.YEAR, year);
        this.calendar.set(Calendar.MONTH, month);
        this.calendar.set(Calendar.DAY_OF_MONTH, day);
        this.calendar.set(Calendar.HOUR, hour);
        this.calendar.set(Calendar.MINUTE, minute);

    }

    public void setDate(Date date){
        this.calendar.setTime(date);
    }

    // update date with string from storage
    public void setStringDate(String dateInString){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        try {
            this.calendar.setTime(sdf.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // get date as string for storage
    public String getStringDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        return sdf.format(this.calendar.getTime());
    }



    // returns the date in string form and iso8601 format
    public String getDate8601(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return df.format(this.calendar.getTime());
    }

    // returns the date as a Date object
    public Date getDate(){
        return this.calendar.getTime();
    }

}
