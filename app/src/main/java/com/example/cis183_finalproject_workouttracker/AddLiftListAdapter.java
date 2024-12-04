package com.example.cis183_finalproject_workouttracker;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class AddLiftListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Lift> lifts;
    ArrayList<String> lt;
    DatabaseHelper db;

    Spinner sp_liftTypes ;
    TextView tv_addLift ;
    TextView tv_removeLift;
    EditText et_weight ;
    EditText et_reps ;
    String selectedLiftType;
    int reps;
    int weight;



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

        Log.d("Updated Index", String.valueOf(i));
        if(i == lifts.size()-1){
            Log.i("Enabled object at", String.valueOf(i));
            tv_addLift.setVisibility(View.VISIBLE);
            tv_removeLift.setVisibility(View.INVISIBLE);
            et_reps.setEnabled(true);
            et_weight.setEnabled(true);
            sp_liftTypes.setEnabled(true);
        }else{
            Log.i("disabled object at", String.valueOf(i));
            tv_addLift.setVisibility(View.INVISIBLE);
            tv_removeLift.setVisibility(View.VISIBLE);
            et_reps.setEnabled(false);
            et_weight.setEnabled(false);
            sp_liftTypes.setEnabled(false);
        }

        fillSpinner(sp_liftTypes);
        spinnerListner();

        setVals(i);
        addNewLift(i);
        removeLift(i);



        return view;
    }

    private void removeLift(int i){
        tv_removeLift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lifts.size() != 1 && i != lifts.size()){
                    lifts.remove(i);
                    Logged.liftsToAdd = lifts;

                    notifyDataSetChanged();
                }



            }
        });
    }

    private void setVals(int i){
        Lift l = lifts.get(i);
        Log.i("SetVals for "+ String.valueOf(i), l.getLiftType()+l.getReps()+","+l.getWeight());
        sp_liftTypes.setSelection(lt.indexOf(l.getLiftType()));
        et_reps.setText(String.valueOf(l.getReps()));
        et_weight.setText(String.valueOf(l.getWeight()));
    }

    private void addNewLift( int i){
        //notifyDataSetChanged();
        tv_addLift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( i == lifts.size()-1){
                    int index = lifts.size()-1;
                    Log.i("INDEX", String.valueOf(index));
                    lifts.get(index).setLiftType(selectedLiftType);
                    lifts.get(index).setLiftTypeID(db.getLiftTypeID(selectedLiftType));
                    lifts.get(index).setReps(reps);
                    lifts.get(index).setWeight(weight);

                    Logged.liftsToAdd = lifts;
                    lifts.add(new Lift());

                    tv_addLift.setVisibility(View.INVISIBLE);
                    tv_removeLift.setVisibility(View.VISIBLE);
                    et_reps.setEnabled(false);
                    et_weight.setEnabled(false);
                    sp_liftTypes.setEnabled(false);

                    logALLLifts();
                    notifyDataSetChanged();
                }else{
                    Log.e("ListView", "All Feilds Not Filled");
                }

            }
        });
    }

    private void getReps(){
        Log.i("setReps", et_reps.getText().toString());
        Log.i("setWeight", et_weight.getText().toString());
    }


    private void spinnerListner(){
        sp_liftTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedLiftType = (String) sp_liftTypes.getItemAtPosition(i);

                //Log.i("Selected spinner index", String.valueOf(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedLiftType = (String) sp_liftTypes.getItemAtPosition(0);
            }
        });

        et_reps.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                EditText r = view.findViewById(R.id.et_alcc_reps);
                reps = Integer.parseInt(r.getText().toString());
                return false;
            }
        });

        et_weight.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                EditText w = view.findViewById(R.id.et_alcc_weight);
                weight =  Integer.parseInt(w.getText().toString());
                return false;
            }
        });


    }


    private void fillSpinner (Spinner sp){
        lt = db.getAllLiftTypes();
        ArrayAdapter adapter =  new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, lt);
        sp.setAdapter(adapter);
    }

    private void logALLLifts(){
        for(Lift lift: lifts){
            Log.i("Lift", lift.getLiftType()+lift.getReps()+","+lift.getWeight());
        }
    }

}
