package com.example.cis183_finalproject_workouttracker;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MyProgress extends AppCompatActivity {
    ArrayList<Lift> lifts;
    DatabaseHelper db;
    ListView lv_userLifts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_progress);
        db = new DatabaseHelper(this);
        lv_userLifts = findViewById(R.id.lv_mp_userLifts);

        lifts = new ArrayList<>();
        db.getAllLiftsGivenUsername(Logged.user.getUserName());
        fillListView();

    }
    private void fillListView(){
        ListAdapter adapter = new LiftListAdapter(this, db.getAllLiftsGivenUsername(Logged.user.getUserName()));
        lv_userLifts.setAdapter(adapter);

    }
}