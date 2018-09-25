package com.example.noahburghardt.burghard_feelsbook;

import android.util.Log;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collection;

public class FeelingListTest extends TestCase {
    public void testEmptyFeelingList(){
        FeelingList feelingList = new FeelingList();
        assertTrue("Empty feeling list", feelingList.size() == 0);
    }
    // test add feeling / get feeling / list size for different emotions
    public void testFeelingList(){
        FeelingList feelingList = new FeelingList();
        Feeling testFeeling = new AngerFeeling();
        feelingList.addFeeling(testFeeling);
        assertTrue("Feeling list size", feelingList.size() == 1);
        assertTrue("", feelingList.contains(testFeeling));
        assertTrue("Angrysize", feelingList.size("anger") == 1);
        assertTrue("Sadsize", feelingList.size("sadness") == 0);

    }

    // test remove feeling
    public void testRemoveFeeling(){
        FeelingList feelingList = new FeelingList();
        Feeling testFeeling = new AngerFeeling();
        feelingList.addFeeling(testFeeling);
        assertTrue("Feeling list size", feelingList.size() == 1);
        assertTrue("", feelingList.contains(testFeeling));
        feelingList.removeFeeling(testFeeling);
        assertTrue("Test student removed", feelingList.size() == 0);
        assertFalse("", feelingList.contains(testFeeling));

    }

    // test list ordering
    public void testFeelingListOrder(){
        AngerFeeling angry = new AngerFeeling();
        AngerFeeling angry2 = new AngerFeeling();
        ArrayList<Feeling> feelings = new ArrayList<Feeling>();
        feelings.add(angry);
        feelings.add(angry2);
        ArrayList<Feeling> feelingsreverse = new ArrayList<Feeling>();
        feelingsreverse.add(angry2);
        feelingsreverse.add(angry);

        // compare initialization order
        FeelingList feelingList = new FeelingList();
        feelingList.addFeeling(angry);
        feelingList.addFeeling(angry2);
        assertTrue("Feeling list order", feelingList.getFeelings().equals(feelings));

        // compare update order
        angry.setDate(1998,0,1);
        assertTrue("Feeling list order", feelingList.getFeelings().equals(feelingsreverse));

    }


}
