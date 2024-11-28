package com.example.cis183_finalproject_workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainMenu extends AppCompatActivity {
    Button btn_pastSessions;
    Button btn_myProgress;
    Button btn_newSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_menu);
        btn_pastSessions = findViewById(R.id.btn_mm_pastsessions);
        btn_myProgress = findViewById(R.id.btn_mm_progress);
        btn_newSession = findViewById(R.id.btn_mm_newsession);

        createNewSession();
        viewMyProgress();
        viewPastSessions();
    }

    private void createNewSession(){
        btn_newSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, NewSession.class));
            }
        });
    }

    private void viewMyProgress(){
        btn_myProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, MyProgress.class));
            }
        });
    }

    private void viewPastSessions(){
        btn_pastSessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, PastSessions.class));

            }
        });
    }
}