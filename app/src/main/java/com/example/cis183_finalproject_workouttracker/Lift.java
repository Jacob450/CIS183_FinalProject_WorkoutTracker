package com.example.cis183_finalproject_workouttracker;

public class Lift {
    private int LiftID;
    private String liftType;
    private int liftTypeID;
    private int reps;
    private int weight;

    public int getLiftID() {
        return LiftID;
    }

    public void setLiftID(int liftID) {
        LiftID = liftID;
    }

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
