package com.example.mop125;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class roommateChecklist extends AppCompatActivity {

    static SharedPreferences sharedPreferences;
    private CheckBox cg1_1,cg1_2,cg1_3,cg1_4,cg1_5,cg1_6,cg1_7,cg1_8,cg2_1,cg2_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roommate_checklist);
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
        editor.putBoolean("cg1_1", cg1_1.isChecked());
        editor.putBoolean("cg1_2", cg1_2.isChecked());
        editor.putBoolean("cg1_3", cg1_3.isChecked());
        editor.putBoolean("cg1_4", cg1_4.isChecked());
        editor.putBoolean("cg1_5", cg1_5.isChecked());
        editor.putBoolean("cg1_6", cg1_6.isChecked());
        editor.putBoolean("cg1_7", cg1_7.isChecked());
        editor.putBoolean("cg1_8", cg1_8.isChecked());
        editor.putBoolean("cg2_1", cg2_1.isChecked());
        editor.putBoolean("cg2_2", cg2_2.isChecked());
        editor.commit();
    }

    private void loadState(){
        cg1_1 = (CheckBox)findViewById(R.id.cg1_1);
        cg1_2 = (CheckBox)findViewById(R.id.cg1_2);
        cg1_3 = (CheckBox)findViewById(R.id.cg1_3);
        cg1_4 = (CheckBox)findViewById(R.id.cg1_4);
        cg1_5 = (CheckBox)findViewById(R.id.cg1_5);
        cg1_6 = (CheckBox)findViewById(R.id.cg1_6);
        cg1_7 = (CheckBox)findViewById(R.id.cg1_7);
        cg1_8 = (CheckBox)findViewById(R.id.cg1_8);
        cg2_1 = (CheckBox)findViewById(R.id.cg2_1);
        cg2_2 = (CheckBox)findViewById(R.id.cg2_2);

        cg1_1.setChecked(sharedPreferences.getBoolean("cg1_1", false));
        cg1_2.setChecked(sharedPreferences.getBoolean("cg1_2", false));
        cg1_3.setChecked(sharedPreferences.getBoolean("cg1_3", false));
        cg1_4.setChecked(sharedPreferences.getBoolean("cg1_4", false));
        cg1_5.setChecked(sharedPreferences.getBoolean("cg1_5", false));
        cg1_6.setChecked(sharedPreferences.getBoolean("cg1_6", false));
        cg1_7.setChecked(sharedPreferences.getBoolean("cg1_7", false));
        cg1_8.setChecked(sharedPreferences.getBoolean("cg1_8", false));
        cg2_1.setChecked(sharedPreferences.getBoolean("cg2_1", false));
        cg2_2.setChecked(sharedPreferences.getBoolean("cg2_2", false));
    }

}