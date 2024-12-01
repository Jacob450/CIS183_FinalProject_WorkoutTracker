package com.example.cis183_finalproject_workouttracker;

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

import java.util.ArrayList;

public class NewSession extends AppCompatActivity {

    ListView lv_liftsToAdd;
    ArrayList<Lift> lifts;
    DatabaseHelper db;
    Button btn_addNewSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_session);
        lv_liftsToAdd = findViewById(R.id.lv_ns_liftstoadd);
        btn_addNewSession = findViewById(R.id.btn_ns_addsession);
        db = new DatabaseHelper(this);


        fillListView();
        confirmNewSession();


    }


    private void confirmNewSession(){
        btn_addNewSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewSession.this, ConfirmNewSession.class));
            }
        });
    }

    private void fillListView(){
        ListAdapter adapter = new NewSessionListAdapter(this);
        lv_liftsToAdd.setAdapter(adapter);
    }


}