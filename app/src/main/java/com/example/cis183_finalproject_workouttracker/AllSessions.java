package com.example.cis183_finalproject_workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class AllSessions extends AppCompatActivity {
    ArrayList<MySession> sessions;
    DatabaseHelper db;
    ListView lv_userLifts;
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_sessions);
        db = new DatabaseHelper(this);
        lv_userLifts = findViewById(R.id.lv_mp_userLifts);
        btn_back = findViewById(R.id.btn_as_back);

        Intent camefrom = getIntent();

        if(camefrom.getExtras() != null){
            String userName = camefrom.getExtras().getString("userName");
            sessions = db.getAllSessionsForUser(userName);
        }else{
            sessions = db.getAllSessionsForUser(Logged.user.getUserName());
        }




        if(sessions!= null){
            fillListView();
            loadSession();
        }


    }

    private void go_back(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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