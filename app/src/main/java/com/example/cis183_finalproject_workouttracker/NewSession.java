package com.example.cis183_finalproject_workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NewSession extends AppCompatActivity {

    ListView lv_liftsToAdd;
    DatabaseHelper db;
    Button btn_addNewSession;
    Button btn_goBack;
    Spinner sp_sessionName;
    String sessionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_session);
        lv_liftsToAdd = findViewById(R.id.lv_ns_liftstoadd);
        btn_addNewSession = findViewById(R.id.btn_ns_addsession);
        btn_goBack = findViewById(R.id.btn_ns_goback);
        sp_sessionName = findViewById(R.id.sp_ns_sessionName);
        db = new DatabaseHelper(this);

        fillNamesSpinner();
        fillListView();
        confirmNewSession();
        goBack();


    }

    private void fillNamesSpinner(){
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, db.getAllSessionNames());
        sp_sessionName.setAdapter(adapter);

        sp_sessionName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("Spinner", (String) sp_sessionName.getItemAtPosition(i));
                sessionName = (String) sp_sessionName.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
                        cns.putExtra("session Name", sessionName);
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