package com.example.mop125;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class myChecklist extends AppCompatActivity {

    static SharedPreferences sharedPreferences;
    static RadioGroup rg_sleeptime;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_checklist);
        initPreferences();
        loadState();
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
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("s_set", String.valueOf((int)rg_sleeptime.getCheckedRadioButtonId()));
        Log.d("whatissavedid", String.valueOf((int)rg_sleeptime.getCheckedRadioButtonId()));
        editor.commit();
    }

    private void loadState(){
        rg_sleeptime = (RadioGroup)findViewById(R.id.rg1_sleeptime);
        int id = 0;
        try {
            id = Integer.parseInt(sharedPreferences.getString("s_set", "0"));
        } catch (NullPointerException e) { }

        Log.d("whatisid", String.valueOf(id));

        if (id == 0) ((RadioButton) findViewById(R.id.rg1_1)).setChecked(true);
        else ((RadioButton) findViewById(id)).setChecked(true);

    }
}