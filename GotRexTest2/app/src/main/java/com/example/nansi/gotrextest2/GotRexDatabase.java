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
    private static final String COL_8 = "Grow";
    private static final String COL_9 = "EatTime";
    private static final String COL_10 = "BathTime";
    private static final String COL_11 = "BedTime";
    private static final String COL_12 = "PlayTime";
    private static final double MAXnormal = 100;
    private static final double MAXbond = 500;
    private static final double MAXgrow = 50;

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
        newGotRexValues.put(COL_3,20);
        newGotRexValues.put(COL_4,30);
        newGotRexValues.put(COL_5,40);
        newGotRexValues.put(COL_6,50);
        newGotRexValues.put(COL_7,10);
        newGotRexValues.put(COL_8,0);
        return db.insert(TABLE_NAME, null, newGotRexValues);
    }


    ///////////// Update Happy/////////////////////////////////

    public void updateHappy(double add){
        //update Query//
        db.execSQL("UPDATE "+TABLE_NAME+" SET "+COL_5+" = "+COL_5+" +"+ add +" WHERE ID=1");
        db.execSQL("UPDATE "+TABLE_NAME+" SET "+COL_7+" = "+COL_7+" +"+ add +" WHERE ID=1");
        //Prepare query for check with MAX//
        String upHap = "SELECT "+COL_5+" FROM "+TABLE_NAME+" WHERE ID=1;";
        ContentValues newSleep = new ContentValues();
        Cursor curHap = db.rawQuery(upHap, null);
        curHap.moveToFirst();
        double checkHap = curHap.getDouble(0);
        ////Check/////
        if(checkHap > MAXnormal){
            db.execSQL("UPDATE "+TABLE_NAME+" SET "+COL_5+" = 100 WHERE ID=1");;
        }
        else ;
    }


    ////////////////////Update Hungry Status/////////////////////////////////

    public void updateEat(int check){
        String upEat ="SELECT "+COL_3+" FROM "+TABLE_NAME+" WHERE ID=1;";
        ContentValues newEat = new ContentValues();
        if(check == 1){
            db.execSQL("UPDATE "+TABLE_NAME+" SET "+COL_3+" = "+COL_3+" +20  WHERE ID=1");
            Cursor curEat = db.rawQuery(upEat, null);
            curEat.moveToFirst();
            Double checkEat = curEat.getDouble(0);
            if(checkEat > MAXnormal){
                db.execSQL("UPDATE "+TABLE_NAME+" SET "+COL_3+" = 100  WHERE ID=1");
            }
        }
        else {
            db.execSQL("UPDATE "+TABLE_NAME+" SET "+COL_3+" = "+COL_3+" +20  WHERE ID=1");
            Cursor curEat = db.rawQuery(upEat, null);
            curEat.moveToFirst();
            Double checkEat = curEat.getDouble(0);
            if(checkEat > MAXnormal){
                db.execSQL("UPDATE "+TABLE_NAME+" SET "+COL_3+" = 100  WHERE ID=1");
            }
        }
    }


    ///////////// Update Energy/////////////////////////////////

    public void updateSleep(){
        //update Query//
        db.execSQL("UPDATE "+TABLE_NAME+" SET "+COL_6+" = "+COL_6+" + 40 WHERE ID=1");
        //Prepare query for check with MAx//
        String upSleep = "SELECT "+COL_6+" FROM "+TABLE_NAME+" WHERE ID=1;";
        ContentValues newSleep = new ContentValues();
        Cursor curSleep = db.rawQuery(upSleep, null);
        curSleep.moveToFirst();
        double checkSleep = curSleep.getDouble(0);
        ////Check/////
        if(checkSleep > MAXnormal){
            db.execSQL("UPDATE "+TABLE_NAME+" SET "+COL_6+" = 100 WHERE ID=1");;
        }
        else ;
    }


//////////// Update Clean Status//////////////////////////

    public void updateBath(){
        //update Query//
        db.execSQL("UPDATE "+TABLE_NAME+" SET "+COL_4+" = "+COL_4+" +50 WHERE ID=1");
        //Prepare query for check with MAx//
        String upBath = "SELECT "+COL_4+" FROM "+TABLE_NAME+" WHERE ID=1;";
        ContentValues newBath = new ContentValues();
        Cursor curBath = db.rawQuery(upBath, null);
        curBath.moveToFirst();
        double checkBath = curBath.getDouble(0);
        ////Check/////
        if(checkBath > MAXnormal){
            db.execSQL("UPDATE "+TABLE_NAME+" SET "+COL_4+" = 100 WHERE ID=1");;
        }
        else ;
    }

    //////////////////Get Status////////////////////////////////

    public int pullStatus(String bar){
        int status;
        if("hungry".equals(bar)){
            Cursor cur = db.rawQuery("SELECT "+COL_3+" FROM "+TABLE_NAME+" WHERE ID =1", null);
            cur.moveToFirst();
            status = cur.getInt(0);
            return status;
        }
        if("clean".equals(bar)){
            Cursor cur = db.rawQuery("SELECT "+COL_4+" FROM "+TABLE_NAME+" WHERE ID =1", null);
            cur.moveToFirst();
            status = cur.getInt(0);
            return status;
        }
        if("energy".equals(bar)){
            Cursor cur = db.rawQuery("SELECT "+COL_6+" FROM "+TABLE_NAME+" WHERE ID =1", null);
            cur.moveToFirst();
            status = cur.getInt(0);
            return status;
        }
        if("happy".equals(bar)){
            Cursor cur = db.rawQuery("SELECT "+COL_5+" FROM "+TABLE_NAME+" WHERE ID =1", null);
            cur.moveToFirst();
            status = cur.getInt(0);
            return status;
        }
        else{
            Cursor cur = db.rawQuery("SELECT "+COL_8+" FROM "+TABLE_NAME+" WHERE ID =1", null);
            cur.moveToFirst();
            status = cur.getInt(0);
            return status;
        }
    }
    /////////////////Pull name to show to status page/////////////////////////////////////

    public String pullName(){
        /*Cursor cur = db.rawQuery("SELECT "+COL_2+" FROM "+TABLE_NAME+" WHERE ID =1", null);
        cur.moveToFirst();
        int columnIndex = cur.getColumnIndex(COL_2);
        String data = cur.getString(columnIndex);*/
        Cursor name = db.query(TABLE_NAME, new String[] {COL_2},
                null, null, null, null, null);
        /*name.moveToFirst();
        int columnIndex = name.getColumnIndex(COL_2);
        String data = name.getString(columnIndex);*/
        name.moveToFirst() ;
        String str = name.getString(name.getColumnIndex(COL_2));
        return str;
    }


    public Cursor seePetInfo(){
        Cursor name = db.query(TABLE_NAME, new String[] {COL_2},
                null, null, null, null, null);
        name.moveToFirst();
        return name;
    }

    ////Create table///////

    private static class gotRexDatabaseOpen extends SQLiteOpenHelper {

        private static final String CREATE_TABLE = " CREATE TABLE " +
                TABLE_NAME + " (" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " TEXT NOT NULL, " + COL_3 +
                " DOUBLE NOT NULL, " + COL_4 + " DOUBLE NOT NULL, " + COL_5 + " DOUBLE NOT NULL, "
                + COL_6 + " DOUBLE NOT NULL, " + COL_7 + " DOUBLE NOT NULL, "+COL_8+" DOUBLE NOT NULL, "+COL_9+" TEXT, "+COL_10+" TEXT, "
                +COL_11+" TEXT, "+COL_12+" TEXT );";

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