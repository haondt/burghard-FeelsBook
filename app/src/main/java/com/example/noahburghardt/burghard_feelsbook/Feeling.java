package com.example.noahburghardt.burghard_feelsbook;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

// Object for storing feelings
public  class Feeling {
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

    // returns the date in string form and iso8601 format
    public String getDateString(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return df.format(this.calendar.getTime());
    }

    // returns the date as a Date object
    public Date getDate(){
        return this.calendar.getTime();
    }
}
