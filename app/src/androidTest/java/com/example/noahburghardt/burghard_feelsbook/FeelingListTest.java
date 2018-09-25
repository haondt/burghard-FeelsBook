package com.example.noahburghardt.burghard_feelsbook;

import android.util.Log;

import junit.framework.TestCase;

import java.util.Collection;

public class FeelingListTest extends TestCase {
    public void testEmptyFeelingList(){
        FeelingList feelingList = new FeelingList();
        Collection<Feeling> feelings = feelingList.getFeelings();
        assertTrue("Empty feeling list", feelings.size() == 0);
    }

    public void testFeelingList(){
        FeelingList feelingList = new FeelingList();
        Feeling testFeeling = new AngryFeeling();
        feelingList.addFeeling(testFeeling);
        Collection<Feeling> feelings = feelingList.getFeelings();
        assertTrue("Feeling list size", feelings.size() == 1);
        assertTrue("", feelings.contains(testFeeling));
    }

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
}
