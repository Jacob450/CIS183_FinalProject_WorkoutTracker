package com.example.cis183_finalproject_workouttracker;

import android.graphics.Color;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Logged
{
    static User user;
    static ArrayList<Lift> liftsToAdd;
    static Lift liftToDelete;
    static ArrayList<MySession> sessionsToCompare;

    static public String getDate(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }



}
