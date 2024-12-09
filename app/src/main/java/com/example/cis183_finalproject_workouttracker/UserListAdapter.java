package com.example.cis183_finalproject_workouttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListAdapter extends BaseAdapter {
    ArrayList<User> users;
    Context context;

    UserListAdapter(Context c, ArrayList<User> u){
        users=u;
        context = c;
    }


    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater mInflator = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflator.inflate(R.layout.userlist_cc, null);
        }
        DatabaseHelper db = new DatabaseHelper(context);
        User u = users.get(i);

        TextView userName = view.findViewById(R.id.tv_ulcc_username);
        TextView name = view.findViewById(R.id.tv_ulcc_name);
        TextView sessions = view.findViewById(R.id.tv_ulcc_sessions);
        TextView bestLift = view.findViewById(R.id.tv_ulcc_bestLift);

        userName.setText(u.getUserName());
        name.setText(u.getFname() + " " +u.getLname());
        sessions.setText(String.valueOf(db.getNumberOfSessions(u.getUserName())) );
        bestLift.setText(db.getBestLift(u.getUserName()).getLiftType());
        return view;
    }
}
