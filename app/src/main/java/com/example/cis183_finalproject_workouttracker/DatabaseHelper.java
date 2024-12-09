package com.example.cis183_finalproject_workouttracker;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String database_name = "workout_tracker.db";
    private static final String users_table_name = "Users";
    private static final String sessions_table_name = "session";
    private static final String lifts_table_name = "lifts";
    private static final String lift_types_table_name = "lift_types";
    private static final String muscle_groups_table = "muscle_groups";
    private static final String session_names_table_name = "session_names";

    public DatabaseHelper(Context c){
        super(c,database_name, null, 13);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + users_table_name + "(userName varchar(50) primary key not null, password varchar(50), fName varchar (50), lName varchar(50), weight integer );");
            db.execSQL("CREATE TABLE " + sessions_table_name + "(sessionID integer primary key autoincrement not null, sessionUserName varchar(50), sessionNameID integer, sessionDate varchar(50));");
            db.execSQL("CREATE TABLE " + lifts_table_name+ "(liftID integer primary key autoincrement not null, sessionID integer, liftTypeID integer, reps integer, weight integer);");
            db.execSQL("CREATE TABLE " + lift_types_table_name + "(liftTypeID integer primary key autoincrement not null, liftName varchar(50), muscleGroupID integer)");
            db.execSQL("CREATE TABLE " + muscle_groups_table + "(muscleGroupID integer primary key autoincrement not null ,muscleGroupName varchar(50))");
            db.execSQL("CREATE TABLE " + session_names_table_name + "(sessionNameID integer primary key autoincrement not null, sessionName varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + users_table_name +";");
        db.execSQL("DROP TABLE IF EXISTS " + sessions_table_name +";");
        db.execSQL("DROP TABLE IF EXISTS " + lifts_table_name +";");
        db.execSQL("DROP TABLE IF EXISTS " + lift_types_table_name +";");
        db.execSQL("DROP TABLE IF EXISTS " + muscle_groups_table +";");
        db.execSQL("DROP TABLE IF EXISTS " + session_names_table_name +";");
        onCreate(db);

    }

    public void initAllTables(){
        initUsers();
        initSessions();
        initLifts();
        initLiftTypes();
        initMuscleGroups();
        initSessionNames();
    }

    public void initSessionNames(){
        SQLiteDatabase db = this.getWritableDatabase();
        if(countRecordsFromTable(session_names_table_name) == 0) {
            db.execSQL("INSERT INTO " + session_names_table_name+"(sessionName) VALUES ('Chest')");
            db.execSQL("INSERT INTO " + session_names_table_name+"(sessionName) VALUES ('Legs')");
            db.execSQL("INSERT INTO " + session_names_table_name+"(sessionName) VALUES ('Back')");
        }


    }

    public void initUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        if(countRecordsFromTable(users_table_name) == 0){
            db.execSQL("INSERT INTO "+ users_table_name +"(userName, password, fname, lname, weight) VALUES ('jperez4', '1234', 'Jacob', 'Perez', '140');");
            db.execSQL("INSERT INTO "+ users_table_name +"(userName, password, fname, lname, weight) VALUES ('dperez', '1111', 'Dylan', 'Perez', '120');");
            db.execSQL("INSERT INTO "+ users_table_name +"(userName, password, fname, lname, weight) VALUES ('juicyj', '0000', 'Justin', 'McDonald', '180');");
        }
        db.close();
    }

    public void initSessions(){
        SQLiteDatabase db = this.getWritableDatabase();
        if(countRecordsFromTable(sessions_table_name) == 0){
            db.execSQL("INSERT INTO "+ sessions_table_name +"(sessionUserName, sessionNameID, sessionDate) VALUES ('jperez4', '1', '11/30/2024');");
            db.execSQL("INSERT INTO "+ sessions_table_name +"(sessionUserName, sessionNameID, sessionDate) VALUES ('dperez', '2', '11/30/2024');");
            db.execSQL("INSERT INTO "+ sessions_table_name +"(sessionUserName, sessionNameID, sessionDate) VALUES ('juicyj', '3', '11/30/2024');");
            db.execSQL("INSERT INTO "+ sessions_table_name +"(sessionUserName, sessionNameID, sessionDate) VALUES ('jperez4', '1', '11/31/2024');");
        }
        db.close();
    }

    public void initLifts(){
        SQLiteDatabase db = this.getWritableDatabase();
        if(countRecordsFromTable(lifts_table_name) == 0){
            //jacobs first session
            db.execSQL("INSERT INTO "+ lifts_table_name +"(sessionID, liftTypeID, reps, weight) VALUES ('1', '1', '8', '185');");
            db.execSQL("INSERT INTO "+ lifts_table_name +"(sessionID, liftTypeID, reps, weight) VALUES ('1', '2', '8', '145');");
            db.execSQL("INSERT INTO "+ lifts_table_name +"(sessionID, liftTypeID, reps, weight) VALUES ('1', '3', '8', '80');");
            //jacobs second session
            db.execSQL("INSERT INTO "+ lifts_table_name +"(sessionID, liftTypeID, reps, weight) VALUES ('4', '1', '6', '195');");
            db.execSQL("INSERT INTO "+ lifts_table_name +"(sessionID, liftTypeID, reps, weight) VALUES ('4', '2', '6', '155');");
            db.execSQL("INSERT INTO "+ lifts_table_name +"(sessionID, liftTypeID, reps, weight) VALUES ('4', '3', '6', '90');");
        }
        db.close();
    }

    public void initLiftTypes(){
        SQLiteDatabase db = this.getWritableDatabase();
        if(countRecordsFromTable(lift_types_table_name) == 0){
            db.execSQL("INSERT INTO "+ lift_types_table_name +"(liftName, muscleGroupID) VALUES ('Bench', '1');");
            db.execSQL("INSERT INTO "+ lift_types_table_name +"(liftName, muscleGroupID) VALUES ('Incline Bench', '2');");
            db.execSQL("INSERT INTO "+ lift_types_table_name +"(liftName, muscleGroupID) VALUES ('Tricep pushdowns', '3');");
        }
        db.close();
    }

    public void initMuscleGroups(){
        SQLiteDatabase db = this.getWritableDatabase();
        if(countRecordsFromTable(muscle_groups_table) == 0){
            db.execSQL("INSERT INTO "+ muscle_groups_table+"(muscleGroupName) VALUES ('Chest');");
            db.execSQL("INSERT INTO "+ muscle_groups_table+"(muscleGroupName) VALUES ('Upper Chest');");
            db.execSQL("INSERT INTO "+ muscle_groups_table+"(muscleGroupName) VALUES ('Triceps');");

        }
        db.close();
    }
    //Deleting from Database

    public void deleteLift(int liftID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + lifts_table_name + " WHERE liftID = '"+liftID+"'");
        db.close();
    }

    //Inserting into DataBase
    public void addSessionToDB(String un, int snID, String sd){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + sessions_table_name + "(sessionUserName, sessionNameID, sessionDate) VALUES ('"+un+"','"+snID+"', '"+sd+"')");
        db.close();

    }

    public void addLiftToDB(int sessionID, Lift lift){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + lifts_table_name + "(sessionID, liftTypeID, reps, weight) VALUES ('"+sessionID+"', '"+lift.getLiftTypeID()+"', '"+lift.getReps()+"', '"+lift.getWeight()+"')");
        db.close();
    }


    //Getter query's=============================================================
    public int getNumberOfSessions(String un){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "SELECT COUNT(*) FROM "+sessions_table_name+" WHERE sessionUserName = '"+un+"';";
        Cursor cursor = db.rawQuery(selectStatement, null);

        if(cursor.moveToFirst() && !cursor.isNull(0)){
            return cursor.getInt(0);
        }else{
            Log.e("DB Error", "Could not get session count" + un);
            return 0;
        }
    }
    public ArrayList<User> getAllUsersExcluding(String un){
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "SELECT userName, password, fname, lname, weight FROM "+users_table_name+";";
        Cursor cursor = db.rawQuery(selectStatement, null);
        if (cursor.moveToFirst()){

            do{
                User u = new User();
                u.setUserName(cursor.getString(0));
                u.setPassword(cursor.getString(1));
                u.setFname(cursor.getString(2));
                u.setLname(cursor.getString(3));
                u.setWeight(cursor.getInt(4));
                users.add(u);
            }while (cursor.moveToNext());
            User utr = Logged.user;
            if(users.remove(utr)){
                Log.e("DB", "Removed " + Logged.user.getUserName());
            }else{
                Log.e("DB", "could not remove " + Logged.user.getUserName());
            }

            return users;
        }else{
            Log.e("DB Error", "Could not get all users ");
            return null;
        }
    }

    public String getDateFromLift(int lID){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "SELECT sessionDate FROM " + sessions_table_name + " INNER JOIN "+lifts_table_name+" on "+sessions_table_name+".sessionID = "+lifts_table_name+".sessionID WHERE liftID = '"+lID+"';";
        Cursor cursor = db.rawQuery(selectStatement, null);

        if(cursor.moveToFirst()){
            return cursor.getString(0);
        }else{
            Log.e("DB Error", "Cannot find Date given liftID "+ lID );
            return null;
        }
    }

    public ArrayList<Lift> getAllLiftsFromUserOfType(String un, int ltID){
        ArrayList<Lift> lifts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "SELECT reps, weight  FROM " + lifts_table_name + " INNER JOIN "+sessions_table_name+" ON "+sessions_table_name+".sessionID = "+lifts_table_name+".sessionID  INNER JOIN " + lift_types_table_name + " ON "+lifts_table_name+".liftTypeID = "+lift_types_table_name+".liftTypeID WHERE sessionUserName = '"+un+"' AND "+lifts_table_name+".liftTypeID = '"+ltID+"';";
        Cursor cursor = db.rawQuery(selectStatement, null);

        if(cursor.moveToFirst()){
            do{
                Lift lift = new Lift();
                lift.setLiftID(ltID);
                lift.setLiftType(getLiftTypeGivenLiftTypeID(ltID));
                lift.setReps(cursor.getInt(0));
                lift.setWeight(cursor.getInt(1));
                Log.i("Lift: ", lift.getLiftType()+", "+lift.getReps()+", "+lift.getWeight());
                lifts.add(lift);
            }while(cursor.moveToNext());
            return lifts;
        }else{
            Log.e("DB Error", "Cannot find lifts given " + un +" and " + String.valueOf(ltID));
            return null;
        }

    }

    public String getSessionName(int snID){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "SELECT sessionName FROM " + session_names_table_name + " WHERE sessionNameID = '"+snID+"';";
        Cursor cursor = db.rawQuery(selectStatement, null);
        if(cursor.moveToFirst()){
            return cursor.getString(0);
        }else{
            Log.e("DB Error", "Cannot find sessionName given " + snID);
            return null;
        }
    }

    public int getSessionNameID(String sn) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "SELECT sessionNameID FROM " + session_names_table_name + " WHERE sessionName = '" + sn + "';";
        Cursor cursor = db.rawQuery(selectStatement, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        } else {
            Log.e("DB Error", "Cannot find sessionName given " + sn);
            return -999;
        }
    }

    public ArrayList<String> getAllSessionNames(){
        ArrayList<String> names = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "SELECT sessionName FROM " + session_names_table_name + ";";
        Cursor cursor = db.rawQuery(selectStatement, null);
        if(cursor.moveToFirst()){
            do{
                names.add(cursor.getString(0));
            }while(cursor.moveToNext());
            return names;
        }else{
            Log.e("DB Error", "Cannot find sessionNames");
            return null;
        }
    }
    public int getLiftTypeID(String liftName){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "SELECT liftTypeID FROM " + lift_types_table_name + " WHERE liftName = '"+liftName+"';";
        Cursor cursor = db.rawQuery(selectStatement, null);
        if(cursor.moveToFirst()){
            return cursor.getInt(0);
        }else{
            Log.e("DB Error", "Could not find lift Type ID Given: "+ liftName);
            return -999;
        }
    }
    public ArrayList<MySession> getAllSessionsForUser(String userName){
        ArrayList<MySession> sessions = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "SELECT sessionID, sessionNameID, sessionDate FROM " + sessions_table_name + " WHERE sessionUserName = '"+userName+"';";
        Cursor cursor = db.rawQuery(selectStatement, null);

        if(cursor.moveToFirst()){
            do{
                MySession s = new MySession();
                s.setID(cursor.getInt(0));
                s.setName(getSessionName(cursor.getInt(1)));
                s.setDate(cursor.getString(2));
                s.setUserName(userName);
                sessions.add(s);
            }while (cursor.moveToNext());
            Collections.reverse(sessions);
            return sessions;
        }else{
            Log.e("DB Error", "Could not find any sessions for user " +userName);
            return null;
        }
    }

    public int getLastSessionIDForUser(String userName){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "SELECT sessionID FROM " + sessions_table_name + " WHERE sessionUserName = '"+userName+"';";
        Cursor cursor = db.rawQuery(selectStatement, null);

        if(cursor.moveToLast()){
            return cursor.getInt(0);
        }else{
            Log.e("DB Error:", "Could not find sessionId given " + userName);
            return -999;
        }
    }

    public ArrayList<Lift> getAllLiftsGivenUsername(String un){
        ArrayList<Integer> sessionIDs = new ArrayList<Integer>();
        //getting session id
        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "SELECT sessionID FROM " + sessions_table_name + " WHERE sessionUserName = '"+un+"';";
        Cursor cursor = db.rawQuery(selectStatement, null);
        //putting all session ids into an array
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                sessionIDs.add(id);
                Log.d("SessionID: ", String.valueOf(id));
            }while (cursor.moveToNext());

            //getting all lifts with matching session id
            ArrayList<Lift> userLifts = new ArrayList<Lift>();
            for(int i =0; sessionIDs.size() != i; i++){
                userLifts.addAll(getLiftsFromSession(sessionIDs.get(i)));
            }
            Collections.reverse(userLifts);
            return userLifts;
        }else{
            Log.e("DB Error:", "Could not find sessionid given " + un);
            return null;
        }




    }

    public ArrayList<Lift> getLiftsFromSession(int SID){
        Lift lift;
        ArrayList<Lift> sessionLifts = new ArrayList<Lift>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "SELECT liftID, liftTypeID, reps, weight FROM " + lifts_table_name + " WHERE sessionID = '"+SID+"';";
        Cursor cursor = db.rawQuery(selectStatement, null);

        if(cursor.moveToFirst()){

            do{
                lift = new Lift();
                lift.setLiftID(cursor.getInt(0));
                lift.setLiftType(getLiftTypeGivenLiftTypeID(cursor.getInt(1)));
                lift.setReps(cursor.getInt(2));
                lift.setWeight(cursor.getInt(3));
                Log.i("Lift: ", lift.getLiftType()+", "+lift.getReps()+", "+lift.getWeight());
                sessionLifts.add(lift);
            }while(cursor.moveToNext());

            return sessionLifts;
        }else{
            Log.e("DB Error: ", "Could not get lifts given Session ID: "+SID);
            return sessionLifts;
        }
    }

    public String getLiftTypeGivenLiftTypeID(int LID){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "SELECT liftName FROM " + lift_types_table_name + " WHERE liftTypeID = '"+LID+"';";
        Cursor cursor = db.rawQuery(selectStatement, null);

        if(cursor.moveToFirst()){
           return cursor.getString(0);
        }else{
            Log.e("DB Error: ", "Could not get lift type given lift type ID: "+LID);
            return null;
        }
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

    public ArrayList<String> getAllLiftTypes(){
        ArrayList<String> lt = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "SELECT liftName FROM " + lift_types_table_name + ";";
        Cursor cursor = db.rawQuery(selectStatement, null);

        if(cursor.moveToFirst()){
            do{
                lt.add(cursor.getString(0));
            }while(cursor.moveToNext());
            return  lt;
        }else{
            Log.e("DB Error:", "Could not find any lift types");
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

    public Lift getBestLift(String un){
        int weight;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "SELECT MAX(weight) FROM " + lifts_table_name + " INNER JOIN "+sessions_table_name+" ON "+sessions_table_name+".sessionID = "+lifts_table_name+".sessionID   WHERE sessionUserName = '"+un+"';";
        Cursor cursor = db.rawQuery(selectStatement, null);

        if(cursor.moveToFirst()) {
            weight = cursor.getInt(0);
            return findLift(un, weight);
        }else{
            Log.e("DB ERROR", "Could not find max weight given " +un);
            return null;
        }
    }

    public Lift findLift(String un, int weight){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectStatement = "SELECT liftID, liftTypeID, reps, weight FROM " + lifts_table_name + " INNER JOIN "+sessions_table_name+" ON "+sessions_table_name+".sessionID = "+lifts_table_name+".sessionID WHERE weight = '"+weight+"' AND sessionUserName = '"+un+"';";
        Cursor cursor = db.rawQuery(selectStatement, null);
        if(cursor.moveToFirst()){
            Lift lift = new Lift();
            lift.setLiftID(cursor.getInt(0));
            lift.setLiftType(getLiftTypeGivenLiftTypeID(cursor.getInt(1)));
            lift.setReps(cursor.getInt(2));
            lift.setWeight(cursor.getInt(3));
            return lift;
        }else{
            Log.e("DB ERROR", "Could not find lift given " +un + weight);
            Lift lift = new Lift();
            lift.setLiftType("NONE");
            return lift;
        }
    }


    public int countRecordsFromTable(String tableName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, tableName);

        return numRows;
    }
}
