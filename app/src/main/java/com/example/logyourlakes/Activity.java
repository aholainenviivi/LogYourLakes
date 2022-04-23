package com.example.logyourlakes;

public class Activity {

    String type;
    String timeSpent;
    String date;
    String lakeName;

    public Activity(String type, String timeSpent, String date, String lakeName) {
        this.type = type;
        this.timeSpent = timeSpent;
        this.date = date;
        this.lakeName = lakeName;
    }

    public String getType() { return type; }

    public String getTimeSpent() { return timeSpent; }

    public String getDate() { return date; }

    public String getLakeName() { return lakeName; }

}
