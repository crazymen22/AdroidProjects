package com.example.personlistproject;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

public class Person implements Parcelable {
    private String name;
    private String surName;
    private Calendar date;
    private String imageName;

    public Person(String name, String surName, Calendar date) {
        this.name = name;
        this.surName = surName;
        this.date = date;
    }

    public Person(String name, String surName, Calendar date, String imageName) {
        this.name = name;
        this.surName = surName;
        this.date = date;
        this.imageName = imageName;
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

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            String name = source.readString();
            String surName = source.readString();
            Calendar date = (Calendar) source.readValue(Calendar.class.getClassLoader());
            String imageName = source.readString();
            return new Person(name, surName, date, imageName);
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

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(surName);
        parcel.writeValue(date);
        parcel.writeString(imageName);
    }
}
