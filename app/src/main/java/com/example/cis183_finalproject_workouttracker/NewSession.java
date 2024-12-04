package com.example.cis183_finalproject_workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NewSession extends AppCompatActivity {

    ListView lv_liftsToAdd;
    DatabaseHelper db;
    Button btn_addNewSession;
    Button btn_goBack;
    EditText et_sessionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_session);
        lv_liftsToAdd = findViewById(R.id.lv_ns_liftstoadd);
        btn_addNewSession = findViewById(R.id.btn_ns_addsession);
        btn_goBack = findViewById(R.id.btn_ns_goback);
        et_sessionName = findViewById(R.id.et_ns_sessionName);
        db = new DatabaseHelper(this);


        fillListView();
        confirmNewSession();


    }

    private void goBack(){
        btn_goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewSession.this, MainMenu.class));

            }
        });
    }


    private void confirmNewSession(){
        btn_addNewSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(!Logged.liftsToAdd.isEmpty()){

                        Intent cns = new Intent(NewSession.this, ConfirmNewSession.class);
                        cns.putExtra("session Name", et_sessionName.getText().toString());
                        startActivity(cns);
                    }


            }
        });
    }

    private void fillListView(){
        ListAdapter adapter = new AddLiftListAdapter(this);
        lv_liftsToAdd.setAdapter(adapter);
    }


}