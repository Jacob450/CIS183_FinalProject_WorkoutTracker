package com.example.cis183_finalproject_workouttracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class NewSession extends AppCompatActivity {

    ListView lv_liftsToAdd;
    DatabaseHelper db;
    Button btn_addNewSession;
    Button btn_goBack;
    Spinner sp_sessionName;
    String sessionName;
    TextView tv_addLiftTypes;
    TextView tv_addMuscleGroups;
    TextView tv_addSessionTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_session);
        lv_liftsToAdd = findViewById(R.id.lv_ns_liftstoadd);
        btn_addNewSession = findViewById(R.id.btn_ns_addsession);
        btn_goBack = findViewById(R.id.btn_ns_goback);
        sp_sessionName = findViewById(R.id.sp_ns_sessionName);
        tv_addMuscleGroups = findViewById(R.id.tv_ns_newMusclegroup);
        tv_addLiftTypes = findViewById(R.id.tv_ns_newLiftType);
        tv_addSessionTypes = findViewById(R.id.tv_ns_newsessionNames);
        db = new DatabaseHelper(this);

        fillNamesSpinner();
        fillListView();
        addNewMuscleGroup();
        addNewLiftType();
        confirmNewSession();
        addNewSessionType();
        goBack();


    }

    private void addNewSessionType(){
        tv_addSessionTypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewSession.this, NewSessionName.class));
            }
        });
    }

    private void addNewLiftType(){
        tv_addLiftTypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewSession.this, NewLiftType.class));
            }
        });
    }

    private  void addNewMuscleGroup(){
        tv_addMuscleGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewSession.this, NewMuscleGroup.class));
            }
        });
    }

    private void fillNamesSpinner(){
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, db.getAllSessionNames());
        sp_sessionName.setAdapter(adapter);

        sp_sessionName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Log.i("Spinner", (String) sp_sessionName.getItemAtPosition(i));
                sessionName = (String) sp_sessionName.getItemAtPosition(i);
                ((TextView) parent.getChildAt(0)).setTextColor(Color.rgb(208,188,255));
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