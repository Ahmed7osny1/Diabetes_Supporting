package com.example.supportingyourdiabetes.data;

public class LogData {

    int value;
    String date;
    String time;

    public LogData(int value, String date, String time) {
        this.value = value;
        this.date = date;
        this.time = time;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
