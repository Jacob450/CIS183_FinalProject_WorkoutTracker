package com.example.cis183_finalproject_workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ProgressMenu extends AppCompatActivity {
    Button btn_compareSessions;
    Button btn_progressGraph;
    Button btn_otherUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_progress_menu);
        btn_compareSessions = findViewById(R.id.btn_pm_CompareSession);
        btn_progressGraph = findViewById(R.id.btn_pm_progressGraph);
        btn_otherUsers = findViewById(R.id.btn_pm_otherUsers);




        compareSessions();
        goToGraph();
        otherUsers();

    }

    private void otherUsers(){
        btn_otherUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProgressMenu.this, CompareToOtherUsers.class));
            }
        });
    }

    private void goToGraph(){
        btn_progressGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProgressMenu.this, ProgressGraph.class));
            }
        });
    }

    private void compareSessions(){
        btn_compareSessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProgressMenu.this, CompareSessions.class));
            }
        });
    }



}