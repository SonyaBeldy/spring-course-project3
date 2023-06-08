package ru.sonyabeldy.springcourse.SpringCourseRestApp.utils;

public class RainyDaysResponse {

    private int count;

    public RainyDaysResponse(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
