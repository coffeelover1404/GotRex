package com.example.nansi.gotrextest2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    private static final String COL_7 = "Bond";
    private static final double MAXnormal = 100;
    private static final double MAXbond = 500;

    private static SQLiteDatabase db;
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
        newGotRexValues.put(COL_7,0);
        return db.insert(TABLE_NAME, null, newGotRexValues);
    }
    public void updateEat(int check){
        ContentValues newEat = new ContentValues();
        if(check == 1){
            newEat.put(COL_3, "(SELECT "+COL_3+" FROM "+ TABLE_NAME +" WHERE ID =1) + 20");
        }
        else newEat.put(COL_3, "(SELECT "+COL_3+" FROM "+ TABLE_NAME +" WHERE ID =1) + 30");

        db.update(TABLE_NAME, newEat, "ID=1", null);
    }


    public Cursor seePetInfo(){
        return db.query(TABLE_NAME, new String[] {COL_2},
                null, null, null, null, null);
    }

    ////Create table///////
    private static class gotRexDatabaseOpen extends SQLiteOpenHelper {

        private static final String CREATE_TABLE = " CREATE TABLE " +
                TABLE_NAME + " (" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " TEXT NOT NULL, " + COL_3 +
                " DOUBLE NOT NULL, " + COL_4 + " DOUBLE NOT NULL, " + COL_5 + " DOUBLE NOT NULL, "
                + COL_6 + " DOUBLE NOT NULL, " + COL_7 + " DOUBLE NOT NULL);";

        public gotRexDatabaseOpen(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            // Create tables again
            onCreate(db);
        }
    }
    ////// Check Empty Function/////////

     public boolean checkDB(){
         boolean empty = true;
         Cursor cur = db.rawQuery("SELECT COUNT(*) FROM "+TABLE_NAME, null);
         if (cur != null && cur.moveToFirst()) {
             empty = (cur.getInt (0) == 0);
         }
         cur.close();
         return empty;
     }

    ////////////////////
}