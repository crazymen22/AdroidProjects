package com.example.personlistproject;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.Calendar;

public class Person implements Parcelable {
    private String name;
    private String surName;
    private Calendar date;
    private String imageName;
    private boolean gender;

    public Person(String name, String surName, Calendar date, boolean gender) {
        this.name = name;
        this.surName = surName;
        this.date = date;
        this.gender = gender;
    }

    public Person(String name, String surName, Calendar date, String imageName, boolean gender) {
        this.name = name;
        this.surName = surName;
        this.date = date;
        this.imageName = imageName;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public Person createFromParcel(Parcel source) {
            String name = source.readString();
            String surName = source.readString();
            Calendar date = (Calendar) source.readValue(Calendar.class.getClassLoader());
            String imageName = source.readString();
            boolean gender = source.readBoolean();
            return new Person(name, surName, date, imageName, gender);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(surName);
        parcel.writeValue(date);
        parcel.writeString(imageName);
        parcel.writeBoolean(gender);
    }
}
