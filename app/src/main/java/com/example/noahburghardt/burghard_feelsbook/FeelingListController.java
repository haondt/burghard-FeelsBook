package com.example.noahburghardt.burghard_feelsbook;

import android.content.SharedPreferences;

public class FeelingListController {
    // Lazy singleton
    private static FeelingList feelingList = null;
    static public FeelingList getFeelingList(){
        if (feelingList == null){
            feelingList = new FeelingList();
        }
        return  feelingList;
    }
}
