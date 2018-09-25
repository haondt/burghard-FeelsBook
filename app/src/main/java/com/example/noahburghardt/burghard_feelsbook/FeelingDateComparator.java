package com.example.noahburghardt.burghard_feelsbook;

class FeelingDateComparator implements java.util.Comparator<Feeling> {
    public int compare(Feeling feeling1, Feeling feeling2){
        return feeling1.getCalendar().compareTo(feeling2.getCalendar());
    }
}
