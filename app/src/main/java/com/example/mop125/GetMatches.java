package com.example.mop125;

    import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class GetMatches extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_matches);

        Button button_main = findViewById(R.id.button1);

        // 현재 사용자 설문 결과와 DB에 저장되어 있는 member들의 설문 결과와 비교
        // 파이어베이스 데이터 읽기
        // 현재 사용자 인지 -> 설문 결과 가져오기
        // 다른 사용자들 설문 결과와 하나씩 비교
        // 같을 시 총 질문수 / 100 * 1 씩 % 높이기
        // 총 일치율 사용자의 데이터에 저장
        // 일치율을 기준으로 사용자들 데이터 내림차순으로 정렬



        button_main.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),Card_MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
