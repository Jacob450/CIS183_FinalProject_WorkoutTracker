package com.example.cis183_finalproject_workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class ConfirmNewSession extends AppCompatActivity {
    ListView lv_liftsToAdd;
    DatabaseHelper db;
    Button btn_goBack;
    Button btn_confirm;
    TextView tv_sessionName;
    String sessionName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirm_new_session);
        lv_liftsToAdd = findViewById(R.id.lv_cns_liftsToAdd);
        btn_goBack = findViewById(R.id.btn_cns_goback);
        btn_confirm = findViewById(R.id.btn_cns_confirm);
        tv_sessionName = findViewById(R.id.tv_cns_sessionName);

        db = new DatabaseHelper(this);
        //Remove dummy lift

        Logged.liftsToAdd.remove(Logged.liftsToAdd.size()-1);
        getSessionName();
        fillListView();
        goBack();
        confirm();


    }

    private void getSessionName(){
        Intent cameFrom = getIntent();
        if(Objects.requireNonNull(cameFrom.getExtras()).getString("session Name") != null){
            sessionName = cameFrom.getExtras().getString("session Name");
            tv_sessionName.setText(sessionName);
        }
    }

    private void confirm(){
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewSession();
                startActivity(new Intent(ConfirmNewSession.this, MainMenu.class));
            }
        });
    }


    private void createNewSession(){
        //Inserting lifts into db
        String username = Logged.user.getUserName();
        String date = Logged.getDate();
        int sessionNameID = db.getSessionNameID(sessionName);

        db.addSessionToDB(username, sessionNameID , date);
        //Inserting lifts into db
        int sessionID = db.getLastSessionIDForUser(username);

        for(Lift lift: Logged.liftsToAdd){
            db.addLiftToDB(sessionID, lift);
        }

    }


    private void goBack(){
        btn_goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newSession = new Intent(ConfirmNewSession.this, NewSession.class);
                newSession.putExtra("FromCNS", "CNS");
                Logged.liftsToAdd.add(new Lift());
                finish();
                //tartActivity(newSession);
            }
        });
    }

    private void fillListView(){
        ListAdapter adapter = new LiftListAdapter(this, Logged.liftsToAdd);
        lv_liftsToAdd.setAdapter(adapter);

    }
}