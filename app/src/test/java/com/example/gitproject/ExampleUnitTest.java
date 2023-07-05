package com.example.gitproject;

import org.junit.Test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void testAdd() {
        RecordList recordList = new RecordList();

        String st_time=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        UserClass userClass = new UserClass("01791294697", "80", "120", "120", "Good", st_time);
        recordList.add(userClass);

        assertEquals(1, recordList.getRecords().size());
        assertTrue(recordList.getRecords().contains(userClass));
    }
}