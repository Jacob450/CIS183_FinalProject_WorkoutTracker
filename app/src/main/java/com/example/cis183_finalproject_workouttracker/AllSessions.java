package com.example.cis183_finalproject_workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AllSessions extends AppCompatActivity {
    ArrayList<MySession> sessions;
    DatabaseHelper db;
    ListView lv_sessions;
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_sessions);
        db = new DatabaseHelper(this);
        lv_sessions = findViewById(R.id.lv_mp_userLifts);
        btn_back = findViewById(R.id.btn_as_back);

        Intent camefrom = getIntent();
        Bundle info = camefrom.getExtras();

        if(info != null){
            String userName = info.getString("username");
            Log.d("Recieved", userName);
            sessions = db.getAllSessionsForUser(userName);
        }else{
            sessions = db.getAllSessionsForUser(Logged.user.getUserName());
        }




        if(sessions!= null){
            fillListView(sessions);
            loadSession();
        }
        deleteSession();
        go_back();


    }

    private void deleteSession(){
        lv_sessions.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int SID = sessions.get(i).getID();
                if(db.deleteSession(SID)){
                    CharSequence text = "Session deleted";
                    Toast toast = Toast.makeText(AllSessions.this, text, Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }else{
                    CharSequence text = "Cannot delete session with lifts";
                    Toast toast = Toast.makeText(AllSessions.this, text, Toast.LENGTH_SHORT);
                    toast.show();
                }
                return false;
            }
        });
    }


    private void go_back(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               startActivity(new Intent(AllSessions.this, MainMenu.class));
            }
        });
    }

    private void loadSession(){
        lv_sessions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //create intent
                Intent viewSession = new Intent(AllSessions.this, ViewSession.class);
                MySession session = sessions.get(i);

                viewSession.putExtra("Session", session);
                startActivity(viewSession);

            }
        });

    }

    private void fillListView(ArrayList<MySession> sessionsList){

        ListAdapter adapter = new SessionListAdapter(this, sessionsList);
        lv_sessions.setAdapter(adapter);

    }
}