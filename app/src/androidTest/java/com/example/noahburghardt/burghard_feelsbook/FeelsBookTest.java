package com.example.noahburghardt.burghard_feelsbook;

import android.util.Log;

import junit.framework.TestCase;
import com.example.noahburghardt.burghard_feelsbook.Feeling;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FeelsBookTest extends TestCase {
    // test get emotion
    public void testFeeling(){
        Feeling angry = new Feeling("anger");
        assertTrue("AngryFeeling is not equal", "angry".equals(angry.getEmotion()));
    }

    //test comment
    public void testFeelingComment(){
        Feeling angry = new Feeling("anger");
        Feeling angryComment = new Feeling("anger");
        angryComment.setComment("I lost 20$ today");
        assertTrue("angry comment is empty", "".equals(angry.getComment()));
        assertTrue("angryComment comment is populated", "I lost 20$ today".equals(angryComment.getComment()));
        angry.setComment("I lost 10$ today");
        assertTrue("angry has new comment", "I lost 10$ today".equals(angry.getComment()));
    }

}
