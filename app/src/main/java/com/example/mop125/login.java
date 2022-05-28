package com.example.mop125;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class login extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private Button join;
    private Button login;
    private EditText email_login;
    private EditText pwd_login;
    private DatabaseReference mDataRef;//실시간 데이터베이스
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();//파이어스토어
    FirebaseAuth firebaseAuth;//회원가입
    String idToken;
    Long myChecklistdone;
    Long rmChecklistdone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize Firebase Auth

        mAuth = FirebaseAuth.getInstance();
        mDataRef = FirebaseDatabase.getInstance().getReference();
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        join = (Button) findViewById(R.id.Signup);
        login = (Button) findViewById(R.id.login);//로그인버튼
        email_login = (EditText) findViewById(R.id.loginEmail);//이메일 입력
        pwd_login = (EditText) findViewById(R.id.loginPassword);//비밀번호 입력
        firebaseAuth = firebaseAuth.getInstance();//firebaseAuth의 인스턴스를 가져옴
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        //idToken = firebaseUser.getUid();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //로그인 버튼을 눌렀을 때
                String email = email_login.getText().toString().trim();
                String pwd = pwd_login.getText().toString().trim();
                //String형 변수 email.pwd(edittext에서 받아오는 값)으로 로그인하는것
                firebaseAuth.signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(com.example.mop125.login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {//성공했을때
                                    Toast.makeText(com.example.mop125.login.this, "환영합니다!", Toast.LENGTH_SHORT).show();

                                    //내꺼, 룸메꺼 설문 완료 여부 파베에서 가져오기
                                    mDataRef.child("userAccount").child(firebaseUser.getUid()).child("myChecklistdone").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            Long value = dataSnapshot.getValue(Long.class);
                                            myChecklistdone = value;
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
                                        }
                                    });

                                    mDataRef.child("userAccount").child(firebaseUser.getUid()).child("rmChecklistdone").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            Long value = dataSnapshot.getValue(Long.class);
                                            rmChecklistdone = value;
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
                                        }
                                    });

                                    Log.d("done", String.valueOf(myChecklistdone));
                                    Log.d("done", String.valueOf(rmChecklistdone));

                                    //설문 완료했으면 여기
                                    if (String.valueOf(myChecklistdone).equals("1") && String.valueOf(rmChecklistdone).equals("1")) {
                                        Intent intent = new Intent(com.example.mop125.login.this, Home.class);
                                        startActivity(intent);
                                    }
                                    //아닐때 여기
                                    else {
                                        Intent intent = new Intent(com.example.mop125.login.this, afterlogin.class);
                                        startActivity(intent);
                                    }
                                } else {//실패했을때
                                    Toast.makeText(com.example.mop125.login.this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override//회원가입 버튼 눌렀을 때
            public void onClick(View v) {
                Intent intent = new Intent(com.example.mop125.login.this, signup.class);
                startActivity(intent);
            }
        });
    }
}