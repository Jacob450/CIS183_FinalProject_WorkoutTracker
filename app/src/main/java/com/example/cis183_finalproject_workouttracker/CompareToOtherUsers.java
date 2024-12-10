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

public class CompareToOtherUsers extends AppCompatActivity {
    ListView lv_users;
    DatabaseHelper db;
    ArrayList<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compare_to_other_users);
        lv_users = findViewById(R.id.lv_ctou_users);
        db = new DatabaseHelper(this);
        users = db.getAllUsersExcluding(Logged.user.getUserName());

        fillListAdapter();
        onListClick();

    }

    private void onListClick(){
        lv_users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String username = users.get(i).getUserName();
                Intent as = new Intent(CompareToOtherUsers.this, AllSessions.class);
                Log.d("Sent", username);
                as.putExtra("username", username);
                startActivity(as);
            }
        });
    }

    private void fillListAdapter(){
        ListAdapter adapter = new UserListAdapter(this, users);
        lv_users.setAdapter(adapter);
    }
}