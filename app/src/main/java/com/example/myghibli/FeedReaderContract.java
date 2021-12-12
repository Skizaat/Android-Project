package com.example.myghibli;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class FeedReaderContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "SavedResults";
        public static final String COLUMN_NAME_PEOPLE = "people";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_SIDEKICK = "sidekick";
        public static final String COLUMN_NAME_VEHICLE = "vehicle";
    }

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_PEOPLE + " TEXT," +
                    FeedEntry.COLUMN_NAME_LOCATION + " TEXT,"+
                    FeedEntry.COLUMN_NAME_SIDEKICK + " TEXT,"+
                    FeedEntry.COLUMN_NAME_VEHICLE + " TEXT)";

    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;


}
