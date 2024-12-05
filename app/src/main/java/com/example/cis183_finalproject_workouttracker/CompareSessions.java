package com.example.cis183_finalproject_workouttracker;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class CompareSessions extends AppCompatActivity {

    ListView lv_sessions;
    DatabaseHelper db;
    ConstraintLayout background;
    ArrayList<Boolean> selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compare_sessions);
        lv_sessions = findViewById(R.id.lv_cs_sessions);
        db = new DatabaseHelper(this);

        selected = new ArrayList<>();
        setSelected();

        fillListView();
        onLVTap();
    }

    private ArrayList<Integer> getSelectedIndex(){
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for(int i = 0; i < selected.size(); i++){
            if(selected.get(i)){
                indexes.add(i);
            }
        }

        for(int i : indexes){
            Log.i("Selected index", String.valueOf(i));
        }
        return indexes;
    }

    private int getSelectedCount(){
        int selectedCount =0;
        for(Boolean bool: selected){
            if(bool){
                selectedCount++;
            }
        }

        return selectedCount;
    }

    private void setSelected(){
        for(MySession session : db.getAllSessionsForUser(Logged.user.getUserName())){
            selected.add(false);
        }
    }
    private void fillListView(){
        ListAdapter adapter = new SessionListAdapter(this, db.getAllSessionsForUser(Logged.user.getUserName()));
        lv_sessions.setAdapter(adapter);
    }

    private void onLVTap(){
        lv_sessions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                background = view.findViewById(R.id.scc_backround);

                if(!selected.get(i) && getSelectedCount() != 2){
                    selected.set(i, true);
                    background.setBackgroundColor(Color.GREEN);
                }else {
                    selected.set(i, false);
                    background.setBackgroundColor(Color.WHITE);
                }
                getSelectedIndex();



            }
        });
    }
}