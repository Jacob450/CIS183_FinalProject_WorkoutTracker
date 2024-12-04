package com.example.cis183_finalproject_workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DeleteLift extends AppCompatActivity {
    Button btn_yes;
    Button btn_no;
    TextView tv_lift;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete_lift);
        btn_no = findViewById(R.id.btn_dl_no);
        btn_yes = findViewById(R.id.btn_dl_yes);
        tv_lift = findViewById(R.id.tv_dl_lift);
        db = new DatabaseHelper(this);

        Lift l = Logged.liftToDelete;
        tv_lift.setText(l.getLiftType() + " Reps: " + l.getReps() + " Weight: "+ l.getWeight());
        Log.d("User", Logged.user.getUserName());
        delete();
        goBack();


    }
    private void goBack(){
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void delete(){
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteLift(Logged.liftToDelete.getLiftID());
                startActivity(new Intent(DeleteLift.this, MyProgress.class));
            }
        });

    }
}