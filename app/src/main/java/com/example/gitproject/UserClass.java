package com.example.gitproject;

public class UserClass {
    String Phone, Systolic, Diastolic, Heart_Rate, Comment, Time;

    /**
     * Default constructor for the UserClass.
     */
    public UserClass() {

    }

    /**
     * Constructor for the UserClass with parameterized values.
     *
     * @param phone
     *      Associated with the measurement.
     * @param systolic
     *      Systolic pressure measurement.
     * @param diastolic
     *      Diastolic pressure measurement.
     * @param heart_Rate
     *      Heart rate measurement.
     * @param comment
     *      Comment for the measurement.
     * @param time
     *      Timestamp of the measurement.
     */
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

    /**
     * Gets the systolic pressure measurement.
     *
     * @return
     *      The systolic pressure.
     */
    public String getSystolic() {
        return Systolic;
    }

    /**
     * Gets the diastolic pressure measurement.
     *
     * @return
     *      The diastolic pressure.
     */
    public String getDiastolic() {
        return Diastolic;
    }

    /**
     * Gets the heart rate measurement.
     *
     * @return
     *      The heart rate.
     */
    public String getHeart_Rate() {
        return Heart_Rate;
    }

    /**
     * Gets the comment for the measurement.
     *
     * @return The comment.
     */
    public String getComment() {
        return Comment;
    }

    /**
     * Gets the timestamp of the measurement.
     *
     * @return The timestamp.
     */
    public String getTime() {
        return Time;
    }

    /**
     * Compares the current UserClass instance with another UserClass instance based on the timestamp.
     *
     * @param userClass
     *      The UserClass instance to compare.
     * @return A negative integer, zero, or a positive integer as the current instance is less than,
     *         equal to, or greater than the specified instance.
     */
    public int compareTo(UserClass userClass) {
        return this.Time.compareTo(userClass.getTime());
    }
}