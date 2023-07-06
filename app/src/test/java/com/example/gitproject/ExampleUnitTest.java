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
    public void testGetRecords() {
        RecordList recordList = new RecordList();

        UserClass userClass = new UserClass("01791294697", "90", "130", "130", "Bad", "12-10-2000 12:30:00");
        recordList.add(userClass);

        UserClass userClass1 = new UserClass("01319661479", "80", "120", "120", "Good", "25-05-2003 10:30:00");
        recordList.add(userClass1);

        assertEquals(0, userClass.compareTo(recordList.getRecords().get(0)));
        assertEquals(0, userClass1.compareTo(recordList.getRecords().get(1)));
    }

    @Test
    public void testAdd() {
        RecordList recordList = new RecordList();

        UserClass userClass = new UserClass("01791294697", "90", "130", "130", "Bad", "12-10-2000 12:30:00");
        recordList.add(userClass);

        UserClass userClass1 = new UserClass("01319661479", "80", "120", "120", "Good", "25-05-2003 10:30:00");
        recordList.add(userClass1);

        assertEquals(2, recordList.getRecords().size());
        assertTrue(recordList.getRecords().contains(userClass));
    }

    @Test
    public void testAddException() {
        RecordList recordList = new RecordList();

        UserClass userClass = new UserClass("01791294697", "90", "130", "130", "Bad", "12-10-2000 12:30:00");
        recordList.add(userClass);

        assertThrows(IllegalArgumentException.class, () -> {
            recordList.add(userClass);
        });
    }

    @Test
    public void testDelete() {
        RecordList recordList = new RecordList();

        UserClass userClass = new UserClass("01791294697", "90", "130", "130", "Bad", "12-10-2000 12:30:00");
        recordList.add(userClass);

        UserClass userClass1 = new UserClass("01319661479", "80", "120", "120", "Good", "25-05-2003 10:30:00");
        recordList.add(userClass1);

        recordList.delete(userClass);

        assertEquals(1, recordList.getRecords().size());
        assertFalse(recordList.getRecords().contains(userClass));
    }

    @Test
    public void testDeleteException() {
        RecordList recordList = new RecordList();

        UserClass userClass = new UserClass("01791294697", "90", "130", "130", "Bad", "12-10-2000 12:30:00");
        recordList.add(userClass);

        UserClass userClass1 = new UserClass("01319661479", "80", "120", "120", "Good", "25-05-2003 10:30:00");
        recordList.add(userClass1);

        recordList.delete(userClass);

        assertThrows(IllegalArgumentException.class, () -> {
            recordList.delete(userClass);
        });
    }

    @Test
    public void testUpdate()
    {
        RecordList recordList = new RecordList();

        UserClass userClass = new UserClass("01791294697", "90", "130", "130", "Bad", "12-10-2000 12:30:00");
        recordList.add(userClass);

        UserClass userClass1 = new UserClass("01319661479", "80", "120", "120", "Good", "12-10-2000 12:30:00");
        recordList.update(0, userClass1);

        assertFalse(recordList.getRecords().contains(userClass));
        assertTrue(recordList.getRecords().contains(userClass1));
    }
}