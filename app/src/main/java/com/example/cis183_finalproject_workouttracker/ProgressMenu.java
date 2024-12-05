package com.example.cis183_finalproject_workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ProgressMenu extends AppCompatActivity {
    Button btn_compareSessions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_progress_menu);
        btn_compareSessions = findViewById(R.id.btn_pm_CompareSession);



        compareSessions();

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