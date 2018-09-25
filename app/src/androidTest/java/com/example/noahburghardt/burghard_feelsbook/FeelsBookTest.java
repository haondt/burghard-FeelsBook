package com.example.noahburghardt.burghard_feelsbook;

import junit.framework.TestCase;
import com.example.noahburghardt.burghard_feelsbook.Feeling;

import java.util.Date;

public class FeelsBookTest extends TestCase {
    public void testFeeling(){
        Date now = new Date();
        AngryFeeling angry = new AngryFeeling(now);
        assertTrue("AngryFeeling is not equal", "angry".equals(angry.getEmotion()));
    }
}
