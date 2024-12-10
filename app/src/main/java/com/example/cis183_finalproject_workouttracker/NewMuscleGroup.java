package com.example.cis183_finalproject_workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewMuscleGroup extends AppCompatActivity {
    EditText et_MuscleGroupName;
    Button btn_confirm;
    Button btn_back;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_muscle_group);
       et_MuscleGroupName = findViewById(R.id.et_nmg_name);
       btn_confirm = findViewById(R.id.btn_nmg_confirm);
       btn_back = findViewById(R.id.btn_nmg_back);
       db = new DatabaseHelper(this);
       confirm();
       back();


    }

    private void confirm(){
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!et_MuscleGroupName.getText().toString().isEmpty()){
                    db.addMuscleGroupToDB(et_MuscleGroupName.getText().toString());
                    startActivity(new Intent(NewMuscleGroup.this, NewSession.class));
                }else{
                    CharSequence text = "Fill out all fields";
                    Toast toast = Toast.makeText(NewMuscleGroup.this, text, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    private void back(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}