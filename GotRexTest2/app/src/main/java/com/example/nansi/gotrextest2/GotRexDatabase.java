package com.example.nansi.gotrextest2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
    private static final double MAXnormal = 100;
    private static final double MAXbond = 300;
    private static final double MAXgrow = 100;

    private static SQLiteDatabase db;
    private final Context context;
    private gotRexDatabaseOpen dbHelper;

    public GotRexDatabase(Context context){
        this.context = context;
        dbHelper = new gotRexDatabaseOpen(this.context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() throws SQLiteException {
        if (db != null && db.isOpen())
            db.close();
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

        newGotRexValues.put(COL_1, 1); //////////////////////////////////////////////////////////////////<------อันนี้คือลงเลขหนึ่งในคอลัมไอดี *************************************
        newGotRexValues.put(COL_2, name);
        newGotRexValues.put(COL_3,10);
        newGotRexValues.put(COL_4,10);
        newGotRexValues.put(COL_5,10);
        newGotRexValues.put(COL_6,10);
        newGotRexValues.put(COL_7,0);
        newGotRexValues.put(COL_8,0);
        return db.insert(TABLE_NAME, null, newGotRexValues);
    }

    ///////////////////Delete Got-Rex///////////////////////////////
    public void deleteGotRex(){
        db.delete(TABLE_NAME,"id=?",new String[]{"1"});
    }

    ///////////////// Check Growth//////////////////////////////////
    public boolean checkGrow(){
        String grow = "SELECT " + COL_8 + " FROM " + TABLE_NAME + " WHERE ID=1;";//Grow

        Cursor curGrow= db.rawQuery(grow, null);//Grow
        curGrow.moveToFirst();
        double checkGrow = curGrow.getDouble(0);

        if (checkGrow >= MAXgrow) {
            return true;
        }
        else
            return false;
    }
    ///////////////////////////// Check Bond ( T-rex or Godzilla)/////////////////////////////
    public boolean checkBond(){
        String bond = "SELECT " + COL_7 + " FROM " + TABLE_NAME + " WHERE ID=1;";//Grow

        Cursor curBond = db.rawQuery(bond, null);//Grow
        curBond.moveToFirst();
        double checkBond = curBond.getDouble(0);

        if (checkBond >= 200) {
            return true;
        }
        else
            return false;
    }

    ////////////// Reduce Status////////////////////////////////
    public void reduceStatus(double time){
        time = time * 10;
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_3 + " = " + COL_3 + " -" + time + " WHERE ID=1");//Hungry
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_4 + " = " + COL_4 + " -" + time + " WHERE ID=1");//Clean
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_5 + " = " + COL_5 + " -" + time + " WHERE ID=1");//Happy
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_6 + " = " + COL_6 + " -" + time + " WHERE ID=1");//Energy

        String eat = "SELECT " + COL_3 + " FROM " + TABLE_NAME + " WHERE ID=1;";
        String clean = "SELECT " + COL_4 + " FROM " + TABLE_NAME + " WHERE ID=1;";
        String hap = "SELECT " + COL_5 + " FROM " + TABLE_NAME + " WHERE ID=1;";
        String energy = "SELECT " + COL_6 + " FROM " + TABLE_NAME + " WHERE ID=1;";

        ///////eat///////
        Cursor curEat = db.rawQuery(eat, null);
        curEat.moveToFirst();
        double checkEat = curEat.getDouble(0);
        if (checkEat < 0) {
            db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_3 + " = 0 WHERE ID=1");
        }

        ///////clean///////
        Cursor curClean = db.rawQuery(clean, null);
        curClean.moveToFirst();
        double checkClean = curClean.getDouble(0);
        if (checkClean < 0) {
            db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_4 + " = 0 WHERE ID=1");
        }

        ///////Happy///////
        Cursor curHap = db.rawQuery(hap, null);
        curHap.moveToFirst();
        double checkHap = curHap.getDouble(0);
        if (checkHap < 0) {
            db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_5 + " = 0 WHERE ID=1");
        }

        ///////Energy///////
        Cursor curEnergy = db.rawQuery(energy, null);
        curEnergy.moveToFirst();
        double checkEnergy = curEnergy.getDouble(0);
        if (checkEnergy < 0) {
            db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_6 + " = 0 WHERE ID=1");
        }
    }


    ///////////// Update Happy/////////////////////////////////

    public void updateHappy(double add) {

        //update Query//
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_5 + " = " + COL_5 + " +" + add + " WHERE ID=1");
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_7 + " = " + COL_7 + " +" + add + " WHERE ID=1");
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_8 + " = " + COL_8 + " +5 WHERE ID=1");

        //Prepare query for check with MAX//
        String upHap = "SELECT " + COL_5 + " FROM " + TABLE_NAME + " WHERE ID=1;";
        String Bond = "SELECT " + COL_7 + " FROM " + TABLE_NAME + " WHERE ID=1;";

        Cursor curHap = db.rawQuery(upHap, null);// Hap
        curHap.moveToFirst();
        double checkHap = curHap.getDouble(0);

        Cursor curBond = db.rawQuery(Bond, null);//Bond
        curBond.moveToFirst();
        double checkBond = curBond.getDouble(0);


        ////Check/////
        if (checkHap > MAXnormal) {
            db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_5 + " = 100 WHERE ID=1");
        }
        if (checkBond > MAXbond) {
            db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_7 + " = 100 WHERE ID=1");
        }

    }



    ////////////////////Update Hungry Status/////////////////////////////////

    public void updateEat(int score){
        String upEat ="SELECT "+COL_3+" FROM "+TABLE_NAME+" WHERE ID=1;";
        db.execSQL("UPDATE "+TABLE_NAME+" SET "+COL_8+" = "+COL_8+" +5 WHERE ID=1");

        db.execSQL("UPDATE "+TABLE_NAME+" SET "+COL_3+" = "+COL_3+" + " +score +" WHERE ID=1");

        Cursor curEat = db.rawQuery(upEat, null);
        curEat.moveToFirst();
        Double checkEat = curEat.getDouble(0);

        if(checkEat > MAXnormal)
            db.execSQL("UPDATE "+TABLE_NAME+" SET "+COL_3+" = 100  WHERE ID=1");

    }


    ///////////// Update Energy/////////////////////////////////

    public void updateSleep(){
        //update Query//
        db.execSQL("UPDATE "+TABLE_NAME+" SET "+COL_6+" = "+COL_6+" + 40 WHERE ID=1");
        db.execSQL("UPDATE "+TABLE_NAME+" SET "+COL_8+" = "+COL_8+" +5 WHERE ID=1");

        //Prepare query for check with MAx//
        String upSleep = "SELECT "+COL_6+" FROM "+TABLE_NAME+" WHERE ID=1;";//Sleep

        Cursor curSleep = db.rawQuery(upSleep, null);
        curSleep.moveToFirst();
        double checkSleep = curSleep.getDouble(0);

        ////Check/////
        if(checkSleep > MAXnormal){
            db.execSQL("UPDATE "+TABLE_NAME+" SET "+COL_6+" = 100 WHERE ID=1");;
        }
    }


//////////// Update Clean Status//////////////////////////

    public void updateBath(){
        //update Query//
        db.execSQL("UPDATE "+TABLE_NAME+" SET "+COL_4+" = "+COL_4+" +50 WHERE ID=1");
        db.execSQL("UPDATE "+TABLE_NAME+" SET "+COL_8+" = "+COL_8+" +5 WHERE ID=1");

        //Prepare query for check with MAx//
        String upBath = "SELECT "+COL_4+" FROM "+TABLE_NAME+" WHERE ID=1;";

        Cursor curBath = db.rawQuery(upBath, null);
        curBath.moveToFirst();
        double checkBath = curBath.getDouble(0);

        ////Check/////
        if(checkBath > MAXnormal){
            db.execSQL("UPDATE "+TABLE_NAME+" SET "+COL_4+" = 100 WHERE ID=1");;
        }
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
        Cursor name = db.query(TABLE_NAME, new String[] {COL_2},
                null, null, null, null, null);
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
                TABLE_NAME + " (" + COL_1 + " INTEGER PRIMARY KEY, " + COL_2 + " TEXT NOT NULL, " + COL_3 + //////////////////////////////////////////////////////////////////<------อันนี้คือตอนสร้างไพรมารี่คีย์ว่าเป็น int*********************
                " DOUBLE NOT NULL, " + COL_4 + " DOUBLE NOT NULL, " + COL_5 + " DOUBLE NOT NULL, "
                + COL_6 + " DOUBLE NOT NULL, " + COL_7 + " DOUBLE NOT NULL, "+COL_8+" DOUBLE NOT NULL );";

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