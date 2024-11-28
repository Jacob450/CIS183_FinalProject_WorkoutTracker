package com.example.cis183_finalproject_workouttracker;

import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirm_new_session);
        lv_liftsToAdd = findViewById(R.id.lv_cns_liftsToAdd);
        db = new DatabaseHelper(this);

        fillListView();

    }

    private void fillListView(){
        ListAdapter adapter = new LiftListAdapter(this, Logged.liftsToAdd);
        lv_liftsToAdd.setAdapter(adapter);

    }
}