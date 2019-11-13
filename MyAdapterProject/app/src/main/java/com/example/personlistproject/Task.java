package com.example.personlistproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {
    private String name;
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;
    private int priority;
    private int categoryResource;

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel source) {
            String name = source.readString();
            int day = source.readInt();
            int month = source.readInt();
            int year = source.readInt();
            int hour = source.readInt();
            int minute = source.readInt();
            int priority = source.readInt();
            int flagResource = source.readInt();
            return new Task(name, day, month, year, hour, minute, priority, flagResource);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public Task(String name, int day, int month, int year, int hour, int minute, int priority, int categoryResource) {
        this.name = name;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.priority = priority;
        this.categoryResource = categoryResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getCategoryResource() {
        return categoryResource;
    }

    public void setCategoryResource(int categoryResource) {
        this.categoryResource = categoryResource;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeInt(day);
        parcel.writeInt(month);
        parcel.writeInt(year);
        parcel.writeInt(hour);
        parcel.writeInt(minute);
        parcel.writeInt(priority);
        parcel.writeInt(categoryResource);
    }
}
