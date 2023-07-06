package com.example.gitproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a list of health records.
 */

public class RecordList {
    ArrayList<UserClass>arrayList = new ArrayList<>();


    public ArrayList<UserClass> getRecords() {
        ArrayList<UserClass> recordList = arrayList;
        return recordList;
    }

    /**
     * Adds one's health records.
     *
     * @param userClass
     *      The health record to be added.
     */
    public void add(UserClass userClass) {
        if(arrayList.contains(userClass))
        {
            throw new IllegalArgumentException();
        }
        arrayList.add(userClass);
    }

    /**
     * Deletes one's health records.
     *
     * @param userClass
     *      The health record to be deleted.
     */
    public void delete(UserClass userClass)
    {
        if(!arrayList.contains(userClass))
        {
            throw new IllegalArgumentException();
        }
        arrayList.remove(userClass);
    }

    /**
     * Updates the health records with new data at the specified position.
     *
     * @param position
     *      The position at which the records are being updated.
     * @param userClass
     *      The updated health record.
     */
    public void update(int position, UserClass userClass)
    {
        arrayList.set(position, userClass);
    }
}