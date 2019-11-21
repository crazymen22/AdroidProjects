package com.example.privateimager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

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

    private Cursor getAllFolderEntries(){
        String[] columns = new String[] {DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_PASSWORD};
        return  database.query(DatabaseHelper.TABLE_FOLDER, columns, null, null, null, null, null);
    }

    public List<Folder> getFolders(){
        ArrayList<Folder> folders = new ArrayList<>();
        Cursor cursor = getAllFolderEntries();
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                String password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD));

                folders.add(new Folder(id, name, password));
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
        return  folders;
    }

    public long getFolderCount(){
        return DatabaseUtils.queryNumEntries(database, DatabaseHelper.TABLE_FOLDER);
    }

    public Folder getFolder(int id){
        Folder folder = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?", DatabaseHelper.TABLE_FOLDER, DatabaseHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            String password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD));

            folder = new Folder(id, name, password);
        }
        cursor.close();
        return  folder;
    }

    public long insertFolder(Folder folder){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, folder.getName());
        cv.put(DatabaseHelper.COLUMN_PASSWORD, folder.getPassword());

        return  database.insert(DatabaseHelper.TABLE_FOLDER, null, cv);
    }

    public long deleteFolder(long folderId){
        String whereClause = "id = ?";
        String[] whereArgs = new String[]{String.valueOf(folderId)};
        return database.delete(DatabaseHelper.TABLE_FOLDER, whereClause, whereArgs);
    }

    public long updateFolder(Folder folder){

        String whereClause = DatabaseHelper.COLUMN_ID + "=" + folder.getId();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, folder.getName());
        cv.put(DatabaseHelper.COLUMN_PASSWORD, folder.getPassword());

        return database.update(DatabaseHelper.TABLE_FOLDER, cv, whereClause, null);
    }

    private Cursor getAllPImageEntries(){
        String[] columns = new String[] {DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_PATH, DatabaseHelper.COLUMN_IDFOLDER};
        return  database.query(DatabaseHelper.TABLE_PIMAGE, columns, null, null, null, null, null);
    }

    public List<PImage> getPImages(){
        ArrayList<PImage> pImages = new ArrayList<>();
        Cursor cursor = getAllFolderEntries();
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                String path = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PATH));
                int idFolder = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_IDFOLDER));

                pImages.add(new PImage(id, name, path, idFolder));
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
        return  pImages;
    }

    public long getPImageCount(){
        return DatabaseUtils.queryNumEntries(database, DatabaseHelper.TABLE_PIMAGE);
    }

    public PImage getPImage(int id){
        PImage pImage = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?", DatabaseHelper.TABLE_FOLDER, DatabaseHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            String path = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PATH));
            int idFolder = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_IDFOLDER));

            pImage = new PImage(id, name, path, idFolder);
        }
        cursor.close();
        return  pImage;
    }

    public long insertPImage(PImage pImage){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, pImage.getName());
        cv.put(DatabaseHelper.COLUMN_PATH, pImage.getPath());
        cv.put(DatabaseHelper.COLUMN_IDFOLDER, pImage.getIdFolder());

        return  database.insert(DatabaseHelper.TABLE_PIMAGE, null, cv);
    }

    public long deletePImage(long pImageId){
        String whereClause = "id = ?";
        String[] whereArgs = new String[]{String.valueOf(pImageId)};
        return database.delete(DatabaseHelper.TABLE_PIMAGE, whereClause, whereArgs);
    }

    public long updatePImage(PImage pImage){

        String whereClause = DatabaseHelper.COLUMN_ID + "=" + pImage.getId();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, pImage.getName());
        cv.put(DatabaseHelper.COLUMN_PATH, pImage.getPath());
        cv.put(DatabaseHelper.COLUMN_IDFOLDER, pImage.getIdFolder());

        return database.update(DatabaseHelper.TABLE_FOLDER, cv, whereClause, null);
    }
}
