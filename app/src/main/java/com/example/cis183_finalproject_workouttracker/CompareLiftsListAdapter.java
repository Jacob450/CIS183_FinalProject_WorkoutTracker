package com.example.cis183_finalproject_workouttracker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CompareLiftsListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Lift> biggerList;
    ArrayList<Lift> smallerList;

    TextView name1;
    TextView weight1;
    TextView reps1;

    TextView name2;
    TextView weight2;
    TextView reps2;

    CompareLiftsListAdapter(ArrayList<Lift> bigger, ArrayList<Lift> smaller, Context c){
        biggerList = bigger;
        smallerList = smaller;
        context = c;
    }

    @Override
    public int getCount() {
        return biggerList.size();
    }

    @Override
    public Object getItem(int i) {
        return biggerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater mInflator = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflator.inflate(R.layout.compare_lifts_cc, null);
        }

        name1 = view.findViewById(R.id.tv_clcc_lift1name);
        weight1 = view.findViewById(R.id.tv_clcc_lift1weight);
        reps1 = view.findViewById(R.id.tv_clcc_lift1reps);

        name2 = view.findViewById(R.id.tv_clcc_lift2Name);
        weight2 = view.findViewById(R.id.tv_clcc_lift2weight);
        reps2 = view.findViewById(R.id.tv_clcc_lift2reps);

        setVals(i);

        return view;
    }

    private void setVals(int i){
        Log.i("Set Index", String.valueOf(i));
        if(biggerList.get(i)!=null){
            Lift l = biggerList.get(i);
            name1.setText(l.getLiftType());
            weight1.setText(String.valueOf(l.getWeight()));
            reps1.setText(String.valueOf(l.getReps()));
        }

        if(smallerList.size() > i ){

            Lift l = smallerList.get(i);
            name2.setText(l.getLiftType());
            weight2.setText(String.valueOf(l.getWeight()));
            reps2.setText(String.valueOf(l.getReps()));

        }else{
            name2.setText("");
            weight2.setText("");
            reps2.setText("");

        }

    }
}
