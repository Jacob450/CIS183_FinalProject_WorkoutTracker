package com.example.cis183_finalproject_workouttracker;

public class Lift {

    String liftType;



    int liftTypeID;
    int reps;
    int weight;

    public int getLiftTypeID() {
        return liftTypeID;
    }

    public void setLiftTypeID(int liftTypeID) {
        this.liftTypeID = liftTypeID;
    }

    public String getLiftType() {
        return liftType;
    }

    public void setLiftType(String liftType) {
        this.liftType = liftType;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
