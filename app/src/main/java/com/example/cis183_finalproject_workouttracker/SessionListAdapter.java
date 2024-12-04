package com.example.cis183_finalproject_workouttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SessionListAdapter extends BaseAdapter {

    Context context;
    ArrayList<MySession> sessions;

    SessionListAdapter(Context c, ArrayList<MySession> s){
        context = c;
        sessions = s;
    }

    @Override
    public int getCount() {
        return sessions.size();
    }

    @Override
    public Object getItem(int i) {
        return sessions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater mInflator = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflator.inflate(R.layout.sessions_custom_cell, null);
        }
        TextView date = view.findViewById(R.id.tv_scc_date);
        TextView name = view.findViewById(R.id.tv_scc_sessionname);

        MySession s = sessions.get(i);

        date.setText(s.getDate());
        name.setText(s.getName());
        return view;
    }
}
