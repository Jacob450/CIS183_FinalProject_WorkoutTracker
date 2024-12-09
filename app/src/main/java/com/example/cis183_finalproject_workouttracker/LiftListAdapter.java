package com.example.cis183_finalproject_workouttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LiftListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Lift> lifts;

    LiftListAdapter(Context c, ArrayList<Lift> l){
        context = c;
        lifts = l;
    }

    @Override
    public int getCount() {
        return lifts.size();
    }

    @Override
    public Object getItem(int i) {
        return lifts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater mInflator = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflator.inflate(R.layout.lifts_custom_cell, null);
        }
        notifyDataSetChanged();

        TextView liftName = view.findViewById(R.id.tv_lcc_liftname);
        TextView reps = view.findViewById(R.id.tv_lcc_reps);
        TextView weight = view.findViewById(R.id.tv_lcc_weight);

        Lift lift = lifts.get(i);

        liftName.setText(lift.getLiftType());
        reps.setText(String.valueOf(lift.getReps()));
        weight.setText(String.valueOf(lift.getWeight()) +"Ibs");


        return view;
    }


}
