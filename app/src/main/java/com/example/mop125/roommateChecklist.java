package com.example.mop125;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class roommateChecklist extends AppCompatActivity {

    static SharedPreferences sharedPreferences;
    private CheckBox cg1_1,cg1_2,cg1_3,cg1_4,cg1_5,cg1_6,cg1_7,cg1_8,cg2_1,cg2_2;
    Button btn_rm;
    FirebaseDatabase database;
    DatabaseReference reference;
    Member member;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roommate_checklist);
        initPreferences();
        loadState();

        reference = database.getInstance().getReference().child("age");
        member = new Member();

        btn_rm = findViewById(R.id.btn_rm);
        cg1_1 = findViewById(R.id.cg1_1);
        cg1_2 = findViewById(R.id.cg1_2);
        cg1_3 = findViewById(R.id.cg1_3);
        cg1_4 = findViewById(R.id.cg1_4);
        cg1_5 = findViewById(R.id.cg1_5);
        cg1_6 = findViewById(R.id.cg1_6);
        cg1_7 = findViewById(R.id.cg1_7);
        cg1_8 = findViewById(R.id.cg1_8);
        cg2_1 = findViewById(R.id.cg2_1);
        cg2_2 = findViewById(R.id.cg2_2);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    i = (int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_rm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cg1_1.isChecked()){
                    member.setAge1(cg1_1.getText().toString());
                    reference.child(String.valueOf(i+1)).setValue(member);
                }
                if(cg1_2.isChecked()){
                    member.setAge2(cg1_2.getText().toString());
                    reference.child(String.valueOf(i+1)).setValue(member);
                }
                if(cg1_3.isChecked()){
                    member.setAge3(cg1_3.getText().toString());
                    reference.child(String.valueOf(i+1)).setValue(member);
                }
                if(cg1_4.isChecked()){
                    member.setAge4(cg1_4.getText().toString());
                    reference.child(String.valueOf(i+1)).setValue(member);
                }
                if(cg1_5.isChecked()){
                    member.setAge5(cg1_5.getText().toString());
                    reference.child(String.valueOf(i+1)).setValue(member);
                }
                if(cg1_6.isChecked()){
                    member.setAge6(cg1_6.getText().toString());
                    reference.child(String.valueOf(i+1)).setValue(member);
                }
                if(cg1_7.isChecked()){
                    member.setAge7(cg1_7.getText().toString());
                    reference.child(String.valueOf(i+1)).setValue(member);
                }
                if(cg1_8.isChecked()){
                    member.setAge8(cg1_8.getText().toString());
                    reference.child(String.valueOf(i+1)).setValue(member);
                }
                if(cg2_1.isChecked()){
                    member.setSmoke1(cg2_1.getText().toString());
                    reference.child(String.valueOf(i+1)).setValue(member);
                }
                if(cg2_2.isChecked()){
                    member.setSmoke2(cg2_2.getText().toString());
                    reference.child(String.valueOf(i+1)).setValue(member);
                }
            }
        });

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