package com.example.noahburghardt.burghard_feelsbook;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FeelingList implements Serializable {
    protected ArrayList<Feeling> feelings;
    private Map<String, Integer> count;

    public FeelingList(){
        this.feelings = new ArrayList<>();
        this.count = new HashMap<String, Integer>();
    }


    // Returns list of feelings
    public ArrayList<Feeling> getFeelings() {
        return this.feelings;
    }

    // Adds feeling to list of feelings
    public void addFeeling(Feeling feeling){
        // add feeling to list of feelings and sort it
        this.feelings.add(feeling);
        Collections.sort(this.feelings, new FeelingDateComparator());

        // if emotion is not in dictionary, add it
        // otherwise increase count by one
        if(this.count.get(feeling.getEmotion()) == null){
            this.count.put(feeling.getEmotion(), 1);
        }
        else{
            this.count.put(feeling.getEmotion(), this.count.get(feeling.getEmotion()) + 1);
        }
    }

    // Removes feeling from list of feelings
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

    // check if feeling is in feelings list
    public boolean contains(Feeling feeling) {
        return this.feelings.contains(feeling);
    }

    // return size of entire feelings list
    public int size() {
        return this.feelings.size();
    }

    // return number of specific emotion in feeling list
    public int size(String emotion) {
        if(this.count.containsKey(emotion)){
            return this.count.get(emotion);
        }
        else{
            return 0;
        }
    }

    // get feeling object at specific position
    public Feeling getFeeling(int position) {
        return this.feelings.get(position);
    }

    // get position of specific feeling object
    public int getFeelingPosition(Feeling feeling) {
        return this.feelings.indexOf(feeling);
    }

    // save current data to file
    public void save(SharedPreferences sharedPref) {
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(this);
        editor.putString("savedFeelings", json);
        editor.commit();
    }

}
