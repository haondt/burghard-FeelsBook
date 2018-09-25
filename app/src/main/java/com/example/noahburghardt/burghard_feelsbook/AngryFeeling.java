package com.example.noahburghardt.burghard_feelsbook;

import java.util.Date;

public class AngryFeeling extends Feeling {
    public AngryFeeling(Date date, String comment){
        super("angry",date,comment);
    }
    public AngryFeeling(Date date) {
        super("angry", date);
    }
    public AngryFeeling(){
        super("angry");
    }

    public String getEmotion(){
        return this.emotion;
    }
}
