package com.example.nansi.gotrextest2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;

public class GotRexDatabase extends Activity {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GotRexTest.db";
    private static final String TABLE_NAME = "GotRex";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "Name";
    private static final String COL_3 = "Hungry";
    private static final String COL_4 = "Clean";
    private static final String COL_5 = "Happy";
    private static final String COL_6 = "Energy";

    private SQLiteDatabase db;
    private final Context context;
    private gotRexDatabaseOpen dbHelper;

    public GotRexDatabase(Context context){
        this.context = context;
        dbHelper = new gotRexDatabaseOpen(this.context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() throws SQLiteException {
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelper.getReadableDatabase();
        }
    }

    public void close() {
        db.close();
    }

    /////naming little Got Rex////////
    public long insertName(String name) {
        ContentValues newGotRexValues = new ContentValues();

        newGotRexValues.put(COL_2, name);
        newGotRexValues.put(COL_3,0);
        newGotRexValues.put(COL_4,0);
        newGotRexValues.put(COL_5,0);
        newGotRexValues.put(COL_6,0);
        return db.insert(TABLE_NAME, null, newGotRexValues);
    }

    ////Create table///////
    public class gotRexDatabaseOpen extends SQLiteOpenHelper {

        private static final String CREATE_TABLE = " CREATE TABLE " +
                TABLE_NAME + " (" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " TEXT NOT NULL, " + COL_3 +
                " INTEGER NOT NULL, " + COL_4 + " INTEGER NOT NULL, " + COL_5 + " INTEGER NOT NULL, " + COL_6 + " INTEGER NOT NULL);";

        public gotRexDatabaseOpen(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
    ////////////////////
}