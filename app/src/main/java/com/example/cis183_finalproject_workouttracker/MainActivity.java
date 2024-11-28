package com.example.cis183_finalproject_workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText et_username;
    EditText et_password;
    TextView tv_error;
    Button btn_login;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        et_username = findViewById(R.id.et_main_username);
        et_password = findViewById(R.id.et_main_password);
        tv_error = findViewById(R.id.tv_main_error);
        btn_login = findViewById(R.id.btn_main_login);
        tv_error.setVisibility(View.INVISIBLE);


        db = new DatabaseHelper(this);
        db.initAllTables();
        login();


    }

    private void login(){
        //set up login button listener

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String un = et_username.getText().toString();
                String pw = et_password.getText().toString();
                //Does the username exist
                if(!db.doesUserNameExist(un)){
                    tv_error.setText("Error: username not found");
                    tv_error.setVisibility(View.VISIBLE);
                    //if username exist does it match the password

                } else if (!db.doesPasswordMatchUsername(pw,un)){
                    et_password.setText("");
                    tv_error.setText("Error: Password is incorrect");
                    tv_error.setVisibility(View.VISIBLE);

                }else{
                    //if username exists and matches password then set logged in user
                    tv_error.setVisibility(View.INVISIBLE);
                    Logged.user = db.getUserGivenUserName(un);
                    startActivity(new Intent(MainActivity.this, MainMenu.class));

                }
            }
        });
    }
}