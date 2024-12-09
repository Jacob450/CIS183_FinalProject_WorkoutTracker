package com.example.cis183_finalproject_workouttracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;

public class CompareSessions extends AppCompatActivity {

    ListView lv_sessions;
    DatabaseHelper db;
    ConstraintLayout background;
    Button btn_compare;

    ArrayList<MySession> userSessions;
    ArrayList<MySession> sessionsToCompare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compare_sessions);
        lv_sessions = findViewById(R.id.lv_cs_sessions);
        btn_compare = findViewById(R.id.btn_cs_compare);
        db = new DatabaseHelper(this);
        userSessions = db.getAllSessionsForUser(Logged.user.getUserName());
        sessionsToCompare = new ArrayList<>();



        fillListView();
        onLVTap();
        compare();


    }

    private void compare(){
        btn_compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logged.sessionsToCompare = new ArrayList<>();
                Logged.sessionsToCompare = sessionsToCompare;

                startActivity(new Intent(CompareSessions.this, CompareLifts.class));

            }
        });
    }



    private void logSessionsToCompare(){
        for(MySession session: sessionsToCompare){
            Log.i("Session", session.getName() +" "+session.getDate()+" "+ session.getUserName());
        }
    }

    private void onLVTap(){
        lv_sessions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                background = view.findViewById(R.id.scc_backround);
                if (sessionsToCompare.contains(userSessions.get(i))){
                    sessionsToCompare.remove(userSessions.get(i));
                    background.setBackgroundColor(Color.WHITE);

                }else if(sessionsToCompare.size() < 2){
                    sessionsToCompare.add(userSessions.get(i));
                    background.setBackgroundColor(Color.GREEN);
                }



                Log.i("tap", String.valueOf(i));
                logSessionsToCompare();
            }
        });
    }




    private void fillListView(){
        ListAdapter adapter = new SessionListAdapter(this, userSessions);
        lv_sessions.setAdapter(adapter);
    }




}