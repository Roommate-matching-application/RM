package com.example.mop125;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class myChecklist extends AppCompatActivity {

    static SharedPreferences sharedPreferences;
    static RadioGroup rg1_sleeptime;
    static RadioGroup rg2_wakeuptime;


    String[] items = {"95", "96", "97", "98", "99", "00", "01", "02","03","04"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_checklist);
        Spinner spinner = findViewById(R.id.spinner_age);
        initPreferences();
        loadState();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, items
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setSelection(sharedPreferences.getInt("spinner_age", 1));
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveState();
    }

    private void initPreferences(){
        sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
    }

    private void saveState(){
        Spinner spinner = findViewById(R.id.spinner_age);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("rg1", String.valueOf((int)rg1_sleeptime.getCheckedRadioButtonId()));
        editor.putString("rg2", String.valueOf((int)rg2_wakeuptime.getCheckedRadioButtonId()));
        //Log.d("whatissavedid", String.valueOf((int)rg_sleeptime.getCheckedRadioButtonId()));
        editor.putInt("spinner_age", spinner.getSelectedItemPosition());
        editor.commit();
    }

    private void loadState(){
        rg1_sleeptime = (RadioGroup)findViewById(R.id.rg1_sleeptime);
        rg2_wakeuptime = (RadioGroup)findViewById(R.id.rg2_wakeuptime);
        int id = 0, id2 = 0;
        try {
            id = Integer.parseInt(sharedPreferences.getString("rg1", "0"));
            id2 = Integer.parseInt(sharedPreferences.getString("rg2", "0"));

        } catch (NullPointerException e) { }

        Log.d("whatisid", String.valueOf(id));

        if (id == 0) ((RadioButton) findViewById(R.id.rg1_1)).setChecked(true);
        else ((RadioButton) findViewById(id)).setChecked(true);

        if (id2 == 0) ((RadioButton) findViewById(R.id.rg2_1)).setChecked(true);
        else ((RadioButton) findViewById(id2)).setChecked(true);
    }
}
