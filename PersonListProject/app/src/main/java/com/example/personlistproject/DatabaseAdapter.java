package com.example.personlistproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class DatabaseAdapter {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context){
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public DatabaseAdapter open(){
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    private Cursor getAllEntries(){
        String[] columns = new String[] {DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_SURNAME,
                DatabaseHelper.COLUMN_DATE, DatabaseHelper.COLUMN_IMAGE_NAME, DatabaseHelper.COLUMN_GENDER};
        return  database.query(DatabaseHelper.TABLE, columns, null, null, null, null, null);
    }

    public List<Person> getPersons(){
        ArrayList<Person> persons = new ArrayList<>();
        Cursor cursor = getAllEntries();
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                String surName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SURNAME));

                SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd", Locale.ENGLISH);
                Calendar date = new GregorianCalendar();
                try {
                    String dateStr = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DATE));
                    date.setTime(df.parse(dateStr));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String imageName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IMAGE_NAME));
                boolean gender = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_GENDER)) > 0;

                persons.add(new Person(id, name, surName, date, imageName, gender));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
//        for (Person person:
//             persons) {
//            this.open();
//            this.delete(person.getId());
//            this.close();
//        }
        return  persons;
    }

    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, DatabaseHelper.TABLE);
    }

    public Person getPerson(int id){
        Person person = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?",DatabaseHelper.TABLE, DatabaseHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            String surName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SURNAME));

            SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd", Locale.ENGLISH);
            Calendar date = new GregorianCalendar();
            try {
                date.setTime(Objects.requireNonNull(df.parse(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DATE)))));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String imageName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IMAGE_NAME));
            boolean gender = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_GENDER)) > 0;
            person = new Person(id, name, surName, date, imageName, gender);
        }
        cursor.close();
        return  person;
    }

    public long insert(Person person){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, person.getName());
        cv.put(DatabaseHelper.COLUMN_SURNAME, person.getSurName());

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
        String date = format1.format(person.getDate().getTime());

        cv.put(DatabaseHelper.COLUMN_DATE, date);
        cv.put(DatabaseHelper.COLUMN_IMAGE_NAME, person.getImageName());
        cv.put(DatabaseHelper.COLUMN_GENDER, person.isGender());

        return  database.insert(DatabaseHelper.TABLE, null, cv);
    }

    public long delete(long personId){
        String whereClause = "id = ?";
        String[] whereArgs = new String[]{String.valueOf(personId)};
        return database.delete(DatabaseHelper.TABLE, whereClause, whereArgs);
    }

    public long update(Person person){

        String whereClause = DatabaseHelper.COLUMN_ID + "=" + person.getId();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, person.getName());
        cv.put(DatabaseHelper.COLUMN_SURNAME, person.getSurName());
        cv.put(DatabaseHelper.COLUMN_DATE, person.getDate().toString());
        cv.put(DatabaseHelper.COLUMN_IMAGE_NAME, person.getImageName());
        cv.put(DatabaseHelper.COLUMN_GENDER, person.isGender());
        return database.update(DatabaseHelper.TABLE, cv, whereClause, null);
    }
}
