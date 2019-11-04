package com.example.myadapterproject;

import android.os.Parcel;
import android.os.Parcelable;

public class State implements Parcelable {
    private String name;
    private String capital;
    private int flagResource;

    public static final Creator<State> CREATOR = new Creator<State>() {
        @Override
        public State createFromParcel(Parcel source) {
            String name = source.readString();
            String capital = source.readString();
            int flagResource = source.readInt();
            return new State(name, capital, flagResource);
        }

        @Override
        public State[] newArray(int size) {
            return new State[size];
        }
    };

    public State(String name, String capital, int flagResource) {
        this.name = name;
        this.capital = capital;
        this.flagResource = flagResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getFlagResource() {
        return flagResource;
    }

    public void setFlagResource(int flagResource) {
        this.flagResource = flagResource;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(capital);
        parcel.writeInt(flagResource);
    }
}
