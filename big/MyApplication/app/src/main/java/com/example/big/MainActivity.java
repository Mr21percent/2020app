package com.example.big;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar alcohol = findViewById(R.id.alcohol);
        alcohol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView tv4 = findViewById(R.id.textView4);
                if (progress==50){
                    tv4.setText(R.string.max);
                }
                else if (progress>40){
                    tv4.setText(R.string._40);
                }
                else if (progress>30){
                    tv4.setText(R.string._30);
                }
                else if (progress>20){
                    tv4.setText(R.string._20);
                }
                else if (progress>10){
                    tv4.setText(R.string._10);
                }
                else if (progress>0){
                    tv4.setText(R.string._0);
                }
                else{
                    tv4.setText(R.string.min);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void start(View v){
        ConstraintLayout start_page = findViewById(R.id.start_page);
        ConstraintLayout show_answer = findViewById(R.id.show_answer);
        ScrollView sv = findViewById(R.id.scroll_view);
        start_page.setVisibility(View.INVISIBLE);
        sv.setVisibility(View.VISIBLE);
        show_answer.setVisibility(View.INVISIBLE); //화면 전환

        sv.fullScroll(View.FOCUS_UP); //화면 최상단 고정
    }

    public void servey_end(View v){
        ConstraintLayout show_answer = findViewById(R.id.show_answer);
        ScrollView sv = findViewById(R.id.scroll_view);
        TextView cocktail_name = findViewById(R.id.cocktail_name);
        TextView show_choice = findViewById(R.id.show_choice);
        TextView tv4 = findViewById(R.id.textView4);
        SeekBar alcohol = findViewById(R.id.alcohol);
        RadioGroup with_who = findViewById(R.id.with_who);
        RadioGroup how_sweet = findViewById(R.id.how_sweet);
        RadioGroup deco = findViewById(R.id.deco);
        RadioGroup coffee_tea = findViewById(R.id.coffee_tea);
        int who = with_who.getCheckedRadioButtonId();
        int taste= how_sweet.getCheckedRadioButtonId();
        int decoration = deco.getCheckedRadioButtonId();
        int coffee = coffee_tea.getCheckedRadioButtonId();

        String [][][][] table = {
                {
                        {{"레인보우 푸스카페", "보드카 선라이즈", "도화"}, {"알렉산더", "그래스호퍼", "블루 라군"}, {"블랙러시안", "프랜치 75", "에그노그"}},
                        {{"위스키 사워", "미도리 사워", "진 리키"}, {"사이드카", "스카이다이빙", "아메리칸 레모네이드"}, {"카미카제", "김렛", "신데렐라"}},
                        {{"올드 패션드","좀비","캄파리 소다"},{"네그로니", "민트 줄렙","스프모니"},{"마티니","파라다이스","진토닉"}}
                },
                {
                        {{"히로시마", "보드카 선라이즈", "도화"}, {"알렉산더", "그래스호퍼", "블루 라군"}, {"갓파더", "프랜치 75", "에그노그"}},
                        {{"위스키 사워", "미도리 사워", "진 리키"}, {"사이드카", "스카이다이빙", "아메리칸 레모네이드"}, {"카미카제", "김렛", "신데렐라"}},
                        {{"올드 패션드","좀비","캄파리 소다"},{"네그로니", "민트 줄렙","스프모니"},{"마티니","파라다이스","진토닉"}}
                }
        }; // 칵테일 이름

        int aa= 0;
        int bb=0;
        int cc=0;
        int dd=0;

        if (who ==-1 || taste ==-1 || decoration ==-1 || coffee ==-1)
            Toast.makeText(this, "모든 선택을 완료해주셔야 합니다.", Toast.LENGTH_SHORT).show(); // 하나라도 선택이 안되었다면 안넘어가고 경고 메시지 보여주기
        else {
                switch (coffee){
                    case R.id.no_coffee:
                        aa=1;
                        break;
                    case R.id.yes_coffee:
                        aa=0;
                        break;
                }

                switch(taste){
                    case R.id.sweet:
                        bb=0;
                        break;
                    case R.id.sour:
                        bb=1;
                        break;
                    case R.id.dry:
                        bb=2;
                        break;
                }
                switch(decoration){
                    case R.id.deco_deco :
                        cc=0;
                        break;
                    case R.id.deco_soso :
                        cc=1;
                        break;
                    case R.id.deco_taste :
                        cc=2;
                        break;
                }
                dd = (50-alcohol.getProgress())/17;
                cocktail_name.setText(table[aa][bb][cc][dd]);
                sv.setVisibility(View.INVISIBLE); // 칵테일 선택


                // 선택지 다시 보여주기
            RadioButton ch1= findViewById(who);
            RadioButton ch2= findViewById(taste);
            RadioButton ch3= findViewById(decoration);
            RadioButton ch4= findViewById(coffee);

            String result = "누구랑 마셔요.   " + ch1.getText().toString()
            +  "\n 이런 맛을 원해요.   " + ch2.getText().toString()
            + "\n 장식은 이정도면 좋겠어요.   " + ch3.getText().toString()
            + "\n 커피에 대한 선호는 이래요.   " + ch4.getText().toString()
            + "\n 주량은 이래요.   "    + tv4.getText().toString();
            show_choice.setText(result); // 선택지 보여주기
            show_answer.setVisibility(View.VISIBLE); //화면 전환
        }

    }
    public void re_servey(View v){
        ConstraintLayout show_answer = findViewById(R.id.show_answer);
        ScrollView sv = findViewById(R.id.scroll_view);
        RadioGroup with_who = findViewById(R.id.with_who);
        RadioGroup how_sweet = findViewById(R.id.how_sweet);
        RadioGroup deco = findViewById(R.id.deco);
        RadioGroup coffee_tea = findViewById(R.id.coffee_tea);
        SeekBar how_much = findViewById(R.id.alcohol);


        with_who.clearCheck();
        deco.clearCheck();
        coffee_tea.clearCheck();
        how_sweet.clearCheck();
        how_much.setProgress(25); // 선택 전부 지우기

        sv.setVisibility(View.VISIBLE);
        show_answer.setVisibility(View.INVISIBLE); //화면 전환

        sv.fullScroll(View.FOCUS_UP); //화면 최상단 고정
    }
}