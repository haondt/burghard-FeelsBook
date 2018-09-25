package com.example.noahburghardt.burghard_feelsbook;


import java.util.Date;

public abstract class Feeling {
    protected String emotion;
    protected Date date;
    protected String comment;

    public Feeling(String emotion, Date date, String comment){
        this.emotion = emotion;
        this.date = date;
        this.comment = comment;
    }
    public Feeling(String emotion, Date date){
        this.emotion = emotion;
        this.date = date;
        this.comment = "";
    }

    public Feeling(String emotion){
        this.emotion = emotion;
        this.date = new Date();
        this.comment = "";
    }

    public abstract String getEmotion();

}
