package com.example.cis183_finalproject_workouttracker;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String database_name = "workout_tracker.db";
    private static final String users_table_name = "Users";
    private static final String sessions_table_name = "session";
    private static final String lifts_table_name = "lifts";
    private static final String lift_types_table_name = "lift_types";
    private static final String muscle_groups_table = "muscle_groups";

    public DatabaseHelper(Context c){
        super(c,database_name, null, 2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + users_table_name + "(userName varchar(50) primary key not null, password varchar(50), fName varchar (50), lName varchar(50), weight integer );");
            db.execSQL("CREATE TABLE " + sessions_table_name + "(sessionID integer primary key autoincrement not null, sessionUserName varchar(50), sessionName varchar(50), sessionDate varchar(50));");
            db.execSQL("CREATE TABLE " + lifts_table_name+ "(liftID integer primary key autoincrement not null, sessionID integer, liftType varchar(50), reps integer, weight integer);");
            db.execSQL("CREATE TABLE " + lift_types_table_name + "(liftName varchar(50) primary key, muscleGroup varchar (50))");
            db.execSQL("CREATE TABLE " + muscle_groups_table + "(muscleGroupName varchar(50) primary key not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + users_table_name +";");
        db.execSQL("DROP TABLE IF EXISTS " + sessions_table_name +";");
        db.execSQL("DROP TABLE IF EXISTS " + lifts_table_name +";");
        db.execSQL("DROP TABLE IF EXISTS " + lift_types_table_name +";");
        db.execSQL("DROP TABLE IF EXISTS " + muscle_groups_table +";");

        onCreate(db);

    }

    public void initUsers(){
        SQLiteDatabase db = this.getReadableDatabase();
        if(countRecordsFromTable(users_table_name) == 0){
            db.execSQL("INSERT INTO "+ users_table_name +"(userName, password, fname, lname, weight) VALUES ('jperez4', '1234', 'Jacob', 'Perez', '140');");
        }
        db.close();
    }



    public User getUserGivenUserName(String un){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "SELECT fName, lName, password, weight FROM " + users_table_name + " WHERE userName = '"+un+"';";
        Cursor cursor = db.rawQuery(selectStatement, null);

        if (cursor.moveToFirst()){
            User u = new User();
            u.setUserName(un);
            u.setFname(cursor.getString(0));
            u.setLname(cursor.getString(1));
            u.setPassword(cursor.getString(2));
            u.setWeight(cursor.getInt(3));
            return u;
        }else{
            Log.e("DB Error:", "Could not find User given: " +un);
            return null;
        }

    }
    //login functions
    public Boolean doesUserNameExist(String un){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "SELECT userName FROM " + users_table_name + " WHERE userName = '"+un+"';";
        Cursor cursor = db.rawQuery(selectStatement, null);

        if(cursor.moveToFirst()){
            return true;
        }else {
            Log.e("DB Error:", "Could not find UserName given: " +un);
            return false;
        }
    }

    public  Boolean doesPasswordMatchUsername(String pw, String un){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "SELECT password FROM " + users_table_name + " WHERE userName = '"+un+"';";
        Cursor cursor = db.rawQuery(selectStatement, null);

        if(cursor.moveToFirst()){
            return pw.equals(cursor.getString(0));
        }else{
            Log.e("DB Error:", "Could not find password given username: " +un);
            return false;
        }
    }



    public int countRecordsFromTable(String tableName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, tableName);

        return numRows;
    }
}
