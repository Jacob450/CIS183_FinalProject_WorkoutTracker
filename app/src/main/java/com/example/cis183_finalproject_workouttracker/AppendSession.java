package com.example.cis183_finalproject_workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class AppendSession extends AppCompatActivity {
    Spinner sp_liftTypes;
    EditText et_reps;
    EditText et_weight;
    DatabaseHelper db;
    Button btn_confirm;
    Button btn_leave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_append_session);
        sp_liftTypes = findViewById(R.id.sp_aps_liftTypes);
        et_reps = findViewById(R.id.et_aps_reps);
        et_weight = findViewById(R.id.et_aps_weight);
        btn_confirm = findViewById(R.id.btn_aps_add);
        btn_leave = findViewById(R.id.btn_aps_back);
        db = new DatabaseHelper(this);

        filSpinner();
        confirm();
        leave();

    }
    private  void leave(){
        btn_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void confirm(){
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_reps.getText().toString().isEmpty() || et_weight.getText().toString().isEmpty()){
                    CharSequence text = "Fill out all fields";
                    Toast toast = Toast.makeText(AppendSession.this, text, Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Lift lift = new Lift();
                    String liftName = (String) sp_liftTypes.getSelectedItem();
                    lift.setLiftTypeID(db.getLiftTypeID(liftName));
                    lift.setReps(Integer.parseInt(et_reps.getText().toString()));
                    lift.setWeight(Integer.parseInt(et_weight.getText().toString()));
                    db.addLiftToDB(getSession().getID(),lift );

                    Intent viewSession = new Intent(AppendSession.this, ViewSession.class);
                    viewSession.putExtra("Session", getSession());
                    startActivity(viewSession);

                }
            }
        });

    }

    private void filSpinner(){

        ArrayAdapter adapter =  new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, db.getAllLiftTypes());
        sp_liftTypes.setAdapter(adapter);
    }

    private MySession getSession(){
        Intent cameFrom = getIntent();
        return (MySession) cameFrom.getSerializableExtra("Session");

    }
}