package com.example.cis183_finalproject_workouttracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConfirmNewSession extends AppCompatActivity {
    ListView lv_liftsToAdd;
    DatabaseHelper db;
    Button btn_goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirm_new_session);
        lv_liftsToAdd = findViewById(R.id.lv_cns_liftsToAdd);
        btn_goBack = findViewById(R.id.btn_cns_goback);
        db = new DatabaseHelper(this);

        fillListView();
        goBack();

    }

    private void goBack(){
        btn_goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newSession = new Intent(ConfirmNewSession.this, NewSession.class);
                newSession.putExtra("FromCNS", "CNS");
                finish();
                //tartActivity(newSession);
            }
        });
    }

    private void fillListView(){
        ListAdapter adapter = new LiftListAdapter(this, Logged.liftsToAdd);
        lv_liftsToAdd.setAdapter(adapter);

    }
}