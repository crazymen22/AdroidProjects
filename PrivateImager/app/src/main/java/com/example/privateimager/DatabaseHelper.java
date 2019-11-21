package com.example.privateimager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "privateimager.db";
    private static final int SCHEMA = 1;
    public static final String TABLE_FOLDER = "folder";
    public static final String TABLE_PIMAGE = "pimage";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PASSWORD = "password";

    public static final String COLUMN_PATH = "path";
    public static final String COLUMN_IDFOLDER = "idfolder";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_FOLDER + " (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
                + " TEXT, " + COLUMN_PASSWORD + " TEXT);");

        db.execSQL("INSERT INTO "+ TABLE_FOLDER +" (" + COLUMN_NAME
                + ", " + COLUMN_PASSWORD + ") VALUES ('Folder1', '123');");

        db.execSQL("CREATE TABLE " + TABLE_PIMAGE + " (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
                + " TEXT, " + COLUMN_PATH + " TEXT," + COLUMN_IDFOLDER + " INTEGER);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_FOLDER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PIMAGE);
        onCreate(db);
    }
}
