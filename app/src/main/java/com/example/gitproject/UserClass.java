package com.example.gitproject;

/**
 * This is a class of User
 */
public class UserClass {
    String Phone, Systolic, Diastolic, Heart_Rate, Comment, Time;
    public UserClass() {
    }
    public UserClass(String phone, String systolic, String diastolic, String heart_Rate, String comment, String time) {
        Phone = phone;
        Systolic = systolic;
        Diastolic = diastolic;
        Heart_Rate = heart_Rate;
        Comment = comment;
        Time = time;
    }


    public String getPhone() {
        return Phone;
    }

    public String getSystolic() {
        return Systolic;
    }

    public String getDiastolic() {
        return Diastolic;
    }

    public String getHeart_Rate() {
        return Heart_Rate;
    }

    public String getComment() {
        return Comment;
    }

    public String getTime() {
        return Time;
    }
}