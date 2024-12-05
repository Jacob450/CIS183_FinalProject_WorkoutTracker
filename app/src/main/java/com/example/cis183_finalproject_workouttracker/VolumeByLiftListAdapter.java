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
    ArrayList<String> liftNames;
    ArrayList<Integer> volumes;
    VolumeByLiftListAdapter(Context c, ArrayList<String> l, ArrayList<Integer> v){
        context = c;
        liftNames = l;
        volumes = v;
    }

    @Override
    public int getCount() {
        return liftNames.size();
    }

    @Override
    public Object getItem(int i) {
        return liftNames.get(i);
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

        liftName.setText(liftNames.get(i));
        volume.setText(String.valueOf(volumes.get(i))+"Ibs");


        return view;
    }

}
