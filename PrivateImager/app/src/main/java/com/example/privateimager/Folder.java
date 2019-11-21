package com.example.privateimager;

import android.os.Parcel;
import android.os.Parcelable;

public class Folder implements Parcelable {
    private int id;
    private String name;
    private String password;

    public Folder() {
    }

    public Folder(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Folder(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static final Creator<Folder> CREATOR = new Creator<Folder>() {
        @Override
        public Folder createFromParcel(Parcel source) {
            int id = source.readInt();
            String name = source.readString();
            String password = source.readString();
            return new Folder(id, name, password);
        }

        @Override
        public Folder[] newArray(int size) {
            return new Folder[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(password);
    }

    @Override
    public String toString() {
        return "id -" + this.id + " name - " + this.name + " password - " + this.password;
    }
}
