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

import java.util.ArrayList;
import java.util.Collections;

public class AllSessions extends AppCompatActivity {
    ArrayList<MySession> sessions;
    DatabaseHelper db;
    ListView lv_userLifts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_progress);
        db = new DatabaseHelper(this);
        lv_userLifts = findViewById(R.id.lv_mp_userLifts);

        sessions = db.getAllSessionsForUser(Logged.user.getUserName());



        fillListView();
        loadSession();

    }

    private void loadSession(){
        lv_userLifts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //create intent
                Intent viewSession = new Intent(AllSessions.this, ViewSession.class);
                MySession session = sessions.get(i);

                viewSession.putExtra("Session", session);
                startActivity(viewSession);
                return false;
            }
        });
    }

    private void fillListView(){

        ListAdapter adapter = new SessionListAdapter(this, db.getAllSessionsForUser(Logged.user.getUserName()));
        lv_userLifts.setAdapter(adapter);

    }
}