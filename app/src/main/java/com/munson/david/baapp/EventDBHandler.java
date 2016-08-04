package com.munson.david.baapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import java.text.SimpleDateFormat;

public class EventDBHandler extends SQLiteOpenHelper {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "events.db";
    public static final String TABLE_EVENTS = "events";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EVENTTITLE = "title";
    public static final String COLUMN_STARTTIME = "starttime";
    public static final String COLUMN_ENDTIME = "endtime";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LOCATION = "location";

    public EventDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE "+ TABLE_EVENTS+ "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY "
                + COLUMN_EVENTTITLE + " TEXT "
                + COLUMN_STARTTIME + " TEXT "
                + COLUMN_ENDTIME + " TEXT "
                + COLUMN_DESCRIPTION + " TEXT "
                + COLUMN_LOCATION + " TEXT "
                + ")";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS");
        onCreate(db);
    }

    public void addEvent(Event e){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,e.getId());
        values.put(COLUMN_EVENTTITLE, e.getTitle());
        values.put(COLUMN_STARTTIME, sdf.format(e.getStartTime()));
        values.put(COLUMN_ENDTIME, sdf.format(e.getEndTime()));
        values.put(COLUMN_DESCRIPTION,e.getDescription());
        values.put(COLUMN_LOCATION,e.getLocation());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_EVENTS,null,values);
        db.close();

    }

    public void deleteEvent(Event e){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_EVENTS + " WHERE " + COLUMN_ID +"=\"" + e.getId() + "\";");
    }





}
