package com.example.mop125;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    private EditText email_join;
    private EditText pwd_join;
    private EditText age_join,name_join;
    private Button btn;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email_join = (EditText) findViewById(R.id.signupEmail);
        pwd_join = (EditText) findViewById(R.id.signupPassword);
        btn = (Button) findViewById(R.id.signupButton);


        firebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = email_join.getText().toString().trim();
                final String pwd = pwd_join.getText().toString().trim();
                //공백인 부분을 제거하고 보여주는 trim();


                firebaseAuth.createUserWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) { // 회원가입이 성공했을 때

                                if (task.isSuccessful()) {

                                    FirebaseUser firebaseUser =firebaseAuth.getCurrentUser();
                                    userAccount account = new userAccount ();
                                    account.setIdToken(firebaseUser.getUid());
                                    account.setEmailId(firebaseUser.getEmail());
                                    account.setPassword(pwd);

                                    //setValue가 데이터 insert
                                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).setValue(account);
                                    Toast.makeText(signup.this, "회원 가입되었습니다.", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(signup.this, login.class);
                                    startActivity(intent);
                                    finish();


                                } else {
                                    Toast.makeText(signup.this, "등록 에러", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });
            }
        });


    }
}