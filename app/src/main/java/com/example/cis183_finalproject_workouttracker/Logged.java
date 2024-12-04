package com.example.cis183_finalproject_workouttracker;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Logged
{
    static User user;
    static ArrayList<Lift> liftsToAdd;
    static Lift liftToDelete;

    static public String getDate(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(date);
    }
}
