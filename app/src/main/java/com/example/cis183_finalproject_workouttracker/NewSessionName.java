package com.example.cis183_finalproject_workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class NewSessionName extends AppCompatActivity {
    EditText et_name;
    Button btn_add;
    Button btn_back;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_session_name);
        et_name = findViewById(R.id.et_nsn_sessionName);
        btn_add = findViewById(R.id.btn_nsn_add);
        btn_back = findViewById(R.id.btn_nsn_back);
        db = new DatabaseHelper(this);

        add();

    }
    private void add(){
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.getAllSessionNames().contains(et_name.getText().toString())){
                    CharSequence text = "Session Name Already Exist";
                    Toast toast = Toast.makeText(NewSessionName.this, text, Toast.LENGTH_SHORT);
                    toast.show();
                }else if(et_name.getText().toString().isEmpty()){
                    CharSequence text = "Fill out Fields";
                    Toast toast = Toast.makeText(NewSessionName.this, text, Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    db.addSessionName(et_name.getText().toString());
                    CharSequence text = "Name Added";
                    Toast toast = Toast.makeText(NewSessionName.this, text, Toast.LENGTH_SHORT);
                    toast.show();
                    startActivity(new Intent(NewSessionName.this, NewSession.class));
                }
            }
        });
    }
}