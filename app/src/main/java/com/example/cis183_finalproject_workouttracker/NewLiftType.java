package com.example.cis183_finalproject_workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class NewLiftType extends AppCompatActivity {
    EditText et_newLiftTypeName;
    Spinner sp_muscleGroups;
    Button btn_confirm;
    Button btn_back;
    DatabaseHelper db;
    ArrayList<String> groups;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_lift_type);
        et_newLiftTypeName = findViewById(R.id.et_nlt_liftTypeName);
        sp_muscleGroups = findViewById(R.id.sp_nlt_muscleGroups);
        btn_confirm = findViewById(R.id.btn_nlt_confirm);
        btn_back = findViewById(R.id.btn_nlt_back);
        db = new DatabaseHelper(this);
        groups = db.getAllMuscleGroups();

        onSpinnerSelect();
        fillSpinner();
        confirm();
        goBack();


    }
    private void goBack(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void onSpinnerSelect(){
        sp_muscleGroups.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("MuscleGroup Change",(String) sp_muscleGroups.getSelectedItem() );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void confirm(){
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.getAllLiftTypes().contains(et_newLiftTypeName.getText().toString())){
                    CharSequence text = "Lift Type Already Exist";
                    Toast toast = Toast.makeText(NewLiftType.this, text, Toast.LENGTH_SHORT);
                    toast.show();
                }else if(!et_newLiftTypeName.getText().toString().isEmpty()){
                    String muscleGroup = (String) sp_muscleGroups.getSelectedItem();
                    int mgID = db.getMuscleGroupID(muscleGroup);
                    db.addLiftTypeToDB(et_newLiftTypeName.getText().toString(), mgID);
                    startActivity(new Intent(NewLiftType.this, NewSession.class));
                }else{
                    CharSequence text = "Fill out all fields";
                    Toast toast = Toast.makeText(NewLiftType.this, text, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }


    private void fillSpinner(){
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, groups);
        sp_muscleGroups.setAdapter(adapter);
    }
}