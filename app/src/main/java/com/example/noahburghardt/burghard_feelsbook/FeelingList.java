package com.example.noahburghardt.burghard_feelsbook;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FeelingList {
    protected ArrayList<Feeling> feelings;
    private Map<String, Integer> count;

    public FeelingList(){
        this.feelings = new ArrayList<>();
        this.count = new HashMap<String, Integer>();
    }

    public ArrayList<Feeling> getFeelings() {
        Collections.sort(this.feelings, new FeelingDateComparator());
        return this.feelings;
    }

    public void addFeeling(Feeling feeling){
        // add feeling to list of feelings
        this.feelings.add(feeling);

        // if emotion is not in dictionary, add it
        // otherwise increase count by one
        if(this.count.get(feeling.getEmotion()) == null){
            this.count.put(feeling.getEmotion(), 1);
        }
        else{
            this.count.put(feeling.getEmotion(), this.count.get(feeling.getEmotion()) + 1);
        }
    }

    public void removeFeeling(Feeling feeling) {
        // Check to make sure feeling is in list
        if(this.feelings.contains(feeling)){
            // remove from list and reduce counter
            this.feelings.remove(feeling);
            this.count.put(feeling.getEmotion(), this.count.get(feeling.getEmotion()) - 1);
        }

        else {
            throw new RuntimeException("Feeling not found");
        }
    }

    public boolean contains(Feeling feeling) {
        return this.feelings.contains(feeling);
    }

    public int size() {
        return this.feelings.size();
    }

    public int size(String emotion) {
        if(this.count.containsKey(emotion)){
            return this.count.get(emotion);
        }
        else{
            return 0;
        }
    }

    public Feeling getFeeling(int position) {
        return this.feelings.get(position);
    }

}
