package com.example.cis183_finalproject_workouttracker;

import android.content.Intent;
import android.health.connect.datatypes.units.Volume;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class ViewSession extends AppCompatActivity {
    TextView tv_date;
    ListView lv_lifts;
    ListView lv_volumes;
    MySession session;
    DatabaseHelper db;
    ArrayList<Lift> lifts;
    ArrayList<Integer> volumes;
    ArrayList<String> unqLiftTypes;
    Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_session);


        db = new DatabaseHelper(this);
        session = getSession();

        tv_date = findViewById(R.id.tv_vs_date);
        lv_lifts = findViewById(R.id.lv_vs_lifts);
        lv_volumes = findViewById(R.id.lv_vs_volumeByLiftList);
        btn_back = findViewById(R.id.btn_vs_back);
        lifts = db.getLiftsFromSession(session.getID());

        tv_date.setText(session.getDate());

        fillLiftListView();
        unqLiftTypes = getUniqueLiftTypes();
        getVolume();

        for(int v : volumes){
            Log.i("Volume", String.valueOf(v));
        }
        fillListView();
    }

    private void goBack(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void fillListView(){
        ListAdapter adapter = new VolumeByLiftListAdapter(this, unqLiftTypes, volumes);
        lv_volumes.setAdapter(adapter);
    }

    private void getVolume(){
        //for every unique liftType;
        volumes = new ArrayList<>();
        for(int i =0; i < unqLiftTypes.size(); i++){
            volumes.add(0);
            for(Lift lift : lifts){
                int volume = lift.getReps() * lift.getWeight();
                if(lift.getLiftType().equals(unqLiftTypes.get(i))){
                    volumes.set(i, volumes.get(i) + volume) ;
                }
            }
        }
    }

    private void fillLiftListView(){
        ListAdapter adapter = new LiftListAdapter(this,lifts);
        lv_lifts.setAdapter(adapter);
    }

    private ArrayList<String> getUniqueLiftTypes(){
        ArrayList<String> liftTypes = new ArrayList<String>();
        for(Lift lift: lifts){
           if(!liftTypes.contains(lift.getLiftType())){
               liftTypes.add(lift.getLiftType());
               Log.d("Unique Lift", lift.getLiftType());
           }
        }
        return  liftTypes;

    }

    private MySession getSession(){
        Intent cameFrom = getIntent();
        return (MySession) cameFrom.getSerializableExtra("Session");

    }
}