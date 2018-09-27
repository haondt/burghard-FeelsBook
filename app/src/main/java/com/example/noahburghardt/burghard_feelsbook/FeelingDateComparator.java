package com.example.noahburghardt.burghard_feelsbook;

// Compares feelings by date, allows them to be sorted
class FeelingDateComparator implements java.util.Comparator<Feeling> {
    public int compare(Feeling feeling1, Feeling feeling2){
        return feeling2.getCalendar().compareTo(feeling1.getCalendar());
    }
}
