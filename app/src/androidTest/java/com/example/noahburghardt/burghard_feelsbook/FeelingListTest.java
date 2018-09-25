package com.example.noahburghardt.burghard_feelsbook;

import android.util.Log;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collection;

public class FeelingListTest extends TestCase {
    public void testEmptyFeelingList(){
        FeelingList feelingList = new FeelingList();
        Collection<Feeling> feelings = feelingList.getFeelings();
        assertTrue("Empty feeling list", feelings.size() == 0);
    }
    // test add feeling / get feeling
    public void testFeelingList(){
        FeelingList feelingList = new FeelingList();
        Feeling testFeeling = new AngryFeeling();
        feelingList.addFeeling(testFeeling);
        Collection<Feeling> feelings = feelingList.getFeelings();
        assertTrue("Feeling list size", feelings.size() == 1);
        assertTrue("", feelings.contains(testFeeling));
    }
    // test remove feeling
    public void testRemoveFeeling(){
        FeelingList feelingList = new FeelingList();
        Feeling testFeeling = new AngryFeeling();
        feelingList.addFeeling(testFeeling);
        Collection<Feeling> feelings = feelingList.getFeelings();
        assertTrue("Feeling list size", feelings.size() == 1);
        assertTrue("", feelings.contains(testFeeling));
        feelingList.removeFeeling(testFeeling);
        assertTrue("Test student removed", feelings.size() == 0);
        assertFalse("", feelings.contains(testFeeling));

    }
    
    // test list ordering
    public void testFeelingListOrder(){
        AngryFeeling angry = new AngryFeeling();
        AngryFeeling angry2 = new AngryFeeling();
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
