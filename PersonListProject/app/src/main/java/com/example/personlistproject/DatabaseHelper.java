package com.example.personlistproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "personstore.db";
    private static final int SCHEMA = 1;
    static final String TABLE = "person";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_IMAGE_NAME = "image_name";
    public static final String COLUMN_GENDER = "gender";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE + " (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
                + " TEXT, " + COLUMN_SURNAME + " TEXT," + COLUMN_DATE
                + " TEXT, " + COLUMN_IMAGE_NAME + " TEXT," + COLUMN_GENDER
                + " INTEGER);");
        // добавление начальных данных
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
                + ", " + COLUMN_SURNAME  + ", " + COLUMN_DATE
                + ", " + COLUMN_GENDER + ") VALUES ('Том', 'Смит', '1999.10.11', 1);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}
