package com.example.cis183_finalproject_workouttracker;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CompareLifts extends AppCompatActivity {
    ArrayList<MySession> sessionsToCompare;
    ArrayList<Lift> session1Lifts;
    ArrayList<Lift> session2Lifts;
    DatabaseHelper db;
    ListView lv_lifts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compare_lifts);
        lv_lifts = findViewById(R.id.lv_cl_listview);
        db = new DatabaseHelper(this);
        setSessions();
        fillListView();



    }


    private void fillListView(){
        if(session1Lifts.size() > session2Lifts.size() || session1Lifts.size() == session2Lifts.size()){
            ListAdapter adapter = new CompareLiftsListAdapter(session1Lifts, session2Lifts, this);
            lv_lifts.setAdapter(adapter);
        }else{
            ListAdapter adapter = new CompareLiftsListAdapter(session2Lifts, session1Lifts, this);
            lv_lifts.setAdapter(adapter);
        }
    }
    private void setSessions(){
        sessionsToCompare = Logged.sessionsToCompare;
        session1Lifts = db.getLiftsFromSession(sessionsToCompare.get(0).getID());
        session2Lifts = db.getLiftsFromSession(sessionsToCompare.get(1).getID());

    }
}