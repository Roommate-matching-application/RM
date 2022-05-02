package com.example.mop125;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class myChecklist extends AppCompatActivity {

    static SharedPreferences sharedPreferences;
    static RadioGroup rg1_sleeptime;
    static RadioGroup rg2_wakeuptime;
    Button btn_my;
    FirebaseDatabase database;
    DatabaseReference reference;
    Member member;

    FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabaseRef;
    int i = 0;

    final String[] items = {"19", "20", "21", "22", "23", "24", "25", "26","27","28"}; //나이
    final String[] items2 = {"가천대학교", "경희대학교", "고려대학교", "서울대학교", "서울과학기술대학교", "성균관대학교",
            "연세대학교", "중앙대학교","홍익대학교","한양대학교"}; //대학교
    String university;
    String idToken;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_checklist);
        Spinner spinner = (Spinner)findViewById(R.id.spinner_age);
        Spinner spinner2 = (Spinner)findViewById(R.id.spinner_university);
        initPreferences();
        loadState();

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();


        reference = database.getInstance().getReference().child("age");
        member = new Member();

        btn_my = findViewById(R.id.btn_my);
        rg1_sleeptime = findViewById(R.id.rg1_sleeptime);
        rg2_wakeuptime = findViewById(R.id.rg2_wakeuptime);

        //firebase
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

        //저장버튼 누르면 firebase에 저장
        btn_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                member.setRg1(String.valueOf((int)rg1_sleeptime.getCheckedRadioButtonId()));
                reference.child(String.valueOf(i+1)).setValue(member);

                member.setRg2(String.valueOf((int)rg2_wakeuptime.getCheckedRadioButtonId()));
                reference.child(String.valueOf(i+1)).setValue(member);

                idToken = firebaseUser.getUid();
                Log.d("id13",idToken);

                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("university").setValue(university);

                Intent intent = new Intent(myChecklist.this, afterlogin.class);
                startActivity(intent);
            }
        });

        //나이 선택 spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, items
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(sharedPreferences.getInt("spinner_age", 1));

        //대학교 선택 spinner
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, items2
        );
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(Integer.parseInt(sharedPreferences.getString("spinner_university","0")));

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int universitiyNum = spinner2.getSelectedItemPosition();

                university = items2[universitiyNum].trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                university = items2[0].trim();
            }
        });
    }


    @Override
    protected void onStop()
    {
        super.onStop();
        saveState();
    }

    private void initPreferences(){
        sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
    }

    private void saveState(){
        Spinner spinner = findViewById(R.id.spinner_age);
        Spinner spinner2 = findViewById(R.id.spinner_university);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("rg1", String.valueOf((int)rg1_sleeptime.getCheckedRadioButtonId()));
        editor.putString("rg2", String.valueOf((int)rg2_wakeuptime.getCheckedRadioButtonId()));

        editor.putInt("spinner_age", spinner.getSelectedItemPosition());
        editor.putString("spinner_university", String.valueOf(spinner2.getSelectedItemPosition()));
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

        if (id == 0) ((RadioButton) findViewById(R.id.rg1_1)).setChecked(true);
        else ((RadioButton) findViewById(id)).setChecked(true);

        if (id2 == 0) ((RadioButton) findViewById(R.id.rg2_1)).setChecked(true);
        else ((RadioButton) findViewById(id2)).setChecked(true);
    }
}
