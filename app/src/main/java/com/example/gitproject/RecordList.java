package com.example.gitproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * This is a class that keeps track of list of Record
 */
public class RecordList {
    ArrayList<UserClass>arrayList = new ArrayList<>();
    /**
     * @param userClass : Record
     */

    public void add(UserClass userClass) {
        if(arrayList.contains(userClass))
        {
            throw new IllegalArgumentException();
        }
        arrayList.add(userClass);
    }

    /**
     * @param userClass : Record
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
     * @param
     * @param : Record
     */

    public ArrayList<UserClass> getRecords() {
        ArrayList<UserClass> recordList = arrayList;
        return recordList;
    }
}
