package com.example.cis183_finalproject_workouttracker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class AddLiftListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Lift> lifts;
    DatabaseHelper db;

    Spinner sp_liftTypes ;
    TextView tv_addLift ;
    TextView tv_removeLift;
    EditText et_weight ;
    EditText et_reps ;



    AddLiftListAdapter(Context c){
        context = c;
        lifts = new ArrayList<>();
        db = new DatabaseHelper(c);
        lifts.add(new Lift());
        Logged.liftsToAdd = new ArrayList<Lift>();
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
            view = mInflator.inflate(R.layout.add_lift_custom_cell, null);
        }
        sp_liftTypes = view.findViewById(R.id.sp_alcc_liftTypes);
        tv_addLift = view.findViewById(R.id.tv_alcc_addnewlift);
        tv_removeLift = view.findViewById(R.id.tv_alcc_removethislift);
        et_weight = view.findViewById(R.id.et_alcc_weight);
        et_reps = view.findViewById(R.id.et_alcc_reps);

        fillSpinner(sp_liftTypes);
        setVals(i);
        addNewLift(i);
        removeLift(i);

        Log.d("Updated Index", String.valueOf(i));
        if(i == lifts.size()-1){
            tv_addLift.setVisibility(View.VISIBLE);
            et_reps.setEnabled(true);
            et_weight.setEnabled(true);
            sp_liftTypes.setEnabled(true);
            tv_removeLift.setVisibility(View.INVISIBLE);
        }

        return view;
    }

    private void removeLift(int i){
        tv_removeLift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lifts.size() != 1){
                    lifts.remove(i);
                    Logged.liftsToAdd.remove(i);

                    notifyDataSetChanged();
                }

                notifyDataSetChanged();

            }
        });
    }

    private void setVals(int i){
        Lift l = lifts.get(i);
        sp_liftTypes.setSelection(l.getLiftTypeID()-1);
        et_reps.setText(String.valueOf(l.getReps()));
        et_weight.setText(String.valueOf(l.getWeight()));

    }

    private void addNewLift( int i){
        tv_addLift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allFieldsFilled() && i == lifts.size()-1){
                    int index = lifts.size()-1;
                    lifts.get(index).setLiftType((String) sp_liftTypes.getSelectedItem());
                    lifts.get(index).setLiftTypeID(sp_liftTypes.getSelectedItemPosition()+1);
                    lifts.get(index).setReps(Integer.parseInt(et_reps.getText().toString()));
                    lifts.get(index).setWeight(Integer.parseInt(et_weight.getText().toString()));

                    Logged.liftsToAdd.add(lifts.get(index));
                    lifts.add(new Lift());
                    tv_addLift.setVisibility(View.INVISIBLE);
                    tv_removeLift.setVisibility(View.VISIBLE);
                    et_reps.setEnabled(false);
                    et_weight.setEnabled(false);
                    sp_liftTypes.setEnabled(false);

                    notifyDataSetChanged();
                }else{
                    Log.e("ListView", "All Feilds Not Filled");
                }

            }
        });
    }


    private boolean allFieldsFilled(){
        if(et_weight.getText().toString().isEmpty() || et_reps.getText().toString().isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    private void fillSpinner (Spinner sp){
        ArrayList<String> lt = db.getAllLiftTypes();
        ArrayAdapter adapter =  new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, lt);
        sp.setAdapter(adapter);
    }

}
