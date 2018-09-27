package com.example.noahburghardt.burghard_feelsbook;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public  class Feeling {
    protected String emotion;
    protected Calendar calendar;
    protected String comment;

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

    public String getDate(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return df.format(this.calendar.getTime());
    }
}
