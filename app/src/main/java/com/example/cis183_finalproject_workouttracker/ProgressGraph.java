package com.example.cis183_finalproject_workouttracker;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LabelFormatter;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ProgressGraph extends AppCompatActivity {
    GraphView graph;
    DatabaseHelper db;
    Spinner sp_liftTypes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_progress_graph);
        graph = findViewById(R.id.gv_pg_graph);
        sp_liftTypes = findViewById(R.id.sp_pg_spinner);
        db = new DatabaseHelper(this);


        //testGraph(graph);



        fillSpinner();
        changeLift();
        graphAllLifts(1);


    }

    private Date formatDate(int id){
        String dateString = db.getDateFromLift(id);
        Log.e("Date",dateString);
        String m = dateString.substring(0,2);
        String d = dateString.substring(3,5);
        String y = dateString.substring(6,10);

        Log.e("Date", y + "-" + m + "-" + d);
        dateString = y + "-" + m + "-" + d;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();
        try {
            date = formatter.parse(dateString);
            Log.e("DATE()", date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }


    private void changeLift(){
        sp_liftTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedLift = (String) sp_liftTypes.getSelectedItem();
                graph.setTitle("Lift Progress (Volumes) for "+selectedLift);
                int ID = db.getLiftTypeID(selectedLift);
                graphAllLifts(ID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void fillSpinner(){
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, db.getAllLiftTypes());
        sp_liftTypes.setAdapter(adapter);
    }

    private void graphAllLifts(int ID){
        graph.removeAllSeries();
        ArrayList<Lift> lifts = db.getAllLiftsFromUserOfType(Logged.user.getUserName(), ID);
        LineGraphSeries <DataPoint> series = new LineGraphSeries<>();
        //DataPoint point = new DataPoint(new Date(), );----------------------------------------

        for (int i=0; i < lifts.size();i++){

            int v = lifts.get(i).getVolume();
            series.appendData(new DataPoint(i+1,v), true, lifts.size());
        }
        graph.addSeries(series);


    }

    private void testGraph( GraphView graph){
        LineGraphSeries <DataPoint> series = new LineGraphSeries<>();
        double x = 1;
        double y = 2;
        for(x =0;x<90;x++){
            y = Math.sin(2*x*.2) -2 *Math.sin(x*.2);
            series.appendData(new DataPoint(x, y),true, 90);
        }

        graph.addSeries(series);
    }
}