package com.example.cis183_finalproject_workouttracker;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Date;

public class PastSessions extends AppCompatActivity {
    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_past_sessions);
        calendar = findViewById(R.id.cv_aps_calendar);

        setCalendarToToday();
        calendarDateChangeListener();


    }

    private void calendarDateChangeListener(){
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                //i = Year, i1 = month INDEX, i2 = day
                Log.d("Date", String.valueOf(calendarView.getDate()));

            }
        });
    }

    private void setCalendarToToday(){
        Date date = new Date();
        calendar.setDate(date.getTime());

    }
}