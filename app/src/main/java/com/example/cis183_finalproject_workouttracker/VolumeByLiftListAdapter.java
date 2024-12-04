package com.example.cis183_finalproject_workouttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class VolumeByLiftListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Lift>lifts;

    VolumeByLiftListAdapter(Context c, ArrayList<Lift> l){
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
            view = mInflator.inflate(R.layout.volume_by_lift_custom_cell, null);
        }
        TextView liftName = view.findViewById(R.id.tv_vblcc_liftname);
        TextView volume = view.findViewById(R.id.tv_vblcc_volume);



        return view;
    }

}
