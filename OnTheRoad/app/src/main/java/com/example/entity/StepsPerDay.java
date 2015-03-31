package com.example.entity;

/**
 * Created by 俊成 on 2015/4/1.
 */
public class StepsPerDay {
    private String date;
    private String steps;

    public StepsPerDay(String date, String steps) {
        this.date = date;
        this.steps = steps;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
