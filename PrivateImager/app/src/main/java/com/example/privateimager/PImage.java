package com.example.privateimager;

import android.os.Parcel;
import android.os.Parcelable;

public class PImage implements Parcelable {
    private int id;
    private String name;
    private String path;
    private int idFolder;

    public PImage() {
    }

    public PImage(int id, String name, String path, int idPImage) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.idFolder = idPImage;
    }

    public PImage(String name, String path, int idPImage) {
        this.name = name;
        this.path = path;
        this.idFolder = idPImage;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(int idFolder) {
        this.idFolder = idFolder;
    }

    public static final Parcelable.Creator<PImage> CREATOR = new Parcelable.Creator<PImage>() {
        @Override
        public PImage createFromParcel(Parcel source) {
            int id = source.readInt();
            String name = source.readString();
            String path = source.readString();
            int idFolder = source.readInt();
            return new PImage(id, name, path, idFolder);
        }

        @Override
        public PImage[] newArray(int size) {
            return new PImage[size];
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
        parcel.writeString(path);
        parcel.writeInt(idFolder);
    }

    @Override
    public String toString() {
        return "id -" + this.id + " name - " + this.name + " path - " + this.path + " idFolder - " + this.idFolder;
    }
}
