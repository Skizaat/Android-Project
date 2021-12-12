package com.example.myghibli;

import static com.example.myghibli.FeedReaderContract.FeedEntry.TABLE_NAME;
import static com.example.myghibli.FeedReaderContract.FeedEntry._ID;
import static com.example.myghibli.FeedReaderContract.FeedEntry.COLUMN_NAME_PEOPLE;
import static com.example.myghibli.FeedReaderContract.FeedEntry.COLUMN_NAME_LOCATION;
import static com.example.myghibli.FeedReaderContract.FeedEntry.COLUMN_NAME_SIDEKICK;
import static com.example.myghibli.FeedReaderContract.FeedEntry.COLUMN_NAME_VEHICLE;
import static com.example.myghibli.FeedReaderContract.SQL_CREATE_ENTRIES;
import static com.example.myghibli.FeedReaderContract.SQL_DELETE_ENTRIES;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Ghibli.db";

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void insertData(String people, String location, String sidekick, String vehicle)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_PEOPLE, people);
        values.put(COLUMN_NAME_LOCATION, location);
        values.put(COLUMN_NAME_SIDEKICK, sidekick);
        values.put(COLUMN_NAME_VEHICLE, vehicle);

        if (db.insert(TABLE_NAME,null, values) == -1) {
            Log.i("JFL", "error inserting in DB");
        }
        else {
            Log.i("JFL", "inserted " + people + " " + location + " " + sidekick + " " + vehicle);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void deleteSave(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        db.delete(DATABASE_NAME, _ID + "=" + id, null);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @SuppressLint("Range")
    public void printData(ArrayAdapter<String> array) {
        String select = new String("SELECT * from " + TABLE_NAME);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        Log.i("JFL", "reading");
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                String displayed =  cursor.getString(cursor.getColumnIndex(COLUMN_NAME_PEOPLE)) + " " +
                                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LOCATION)) + " " +
                                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_SIDEKICK)) + " " +
                                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME_VEHICLE));
                array.add(displayed);
                Log.i("JFL", "read " + displayed);
            } while (cursor.moveToNext());
        }
    }
}
