package com.example.cis183_finalproject_workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;

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

        if(Logged.user != null){
            lifts = db.getAllLiftsGivenUsername(Logged.user.getUserName());
            Collections.reverse(lifts);
        }



        fillListView();
        deleteLift();

    }

    private void deleteLift(){
        lv_userLifts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("Clicked", lifts.get(i).getLiftType());
                Logged.liftToDelete = lifts.get(i);

                startActivity(new Intent(MyProgress.this, DeleteLift.class));
                return false;
            }
        });
    }

    private void fillListView(){

        ListAdapter adapter = new SessionListAdapter(this, db.getAllSessionsForUser(Logged.user.getUserName()));
        lv_userLifts.setAdapter(adapter);

    }
}