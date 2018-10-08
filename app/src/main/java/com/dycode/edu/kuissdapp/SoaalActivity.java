package com.dycode.edu.kuissdapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class SoaalActivity extends AppCompatActivity {

    public static final String EXTRA_SCORE="extraScore";
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private TextView text_score;
    private TextView text_waktu;
    private TextView text_no;
    private TextView text_soal;
    private RadioGroup radiog;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button btn_lnjt;


    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultcd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private List<Soal> soalList;
    private int soalCounter;
    private int soalCountTotal;
    private Soal currentSoal;

    private int score;
    private boolean jawabann;
    private long backPressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soaal);

        text_score = findViewById(R.id.score);
        text_waktu = findViewById(R.id.waktu);
        text_no = findViewById(R.id.text_no);
        text_soal = findViewById(R.id.text_soal);
        radiog = findViewById(R.id.radiog);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        btn_lnjt = findViewById(R.id.btn_lnjt);

        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultcd = text_waktu.getTextColors();

        KuisDbHelper dbHelper = new KuisDbHelper(this);
        soalList = dbHelper.getAllSoal();
        soalCountTotal = soalList.size();
        Collections.shuffle(soalList);

        showNextSoal();

        btn_lnjt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!jawabann){
                    if(rb1.isChecked()||rb2.isChecked()||rb3.isChecked()||rb4.isChecked()){
                        checkJawaban();
                    }else{
                        Toast.makeText(SoaalActivity.this,"",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    showNextSoal();
                }
            }
        });

    }

    private void showNextSoal() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb4.setTextColor(textColorDefaultRb);
        radiog.clearCheck();

        if(soalCounter < soalCountTotal){
            currentSoal = soalList.get(soalCounter);

            rb1.setText(currentSoal.getPilihan1());
            rb2.setText(currentSoal.getPilihan2());
            rb3.setText(currentSoal.getPilihan3());
            rb4.setText(currentSoal.getPilihan4());

            soalCounter++;
            text_no.setText("Soal : " + soalCounter + "/" + soalCountTotal);
            jawabann = false;
            btn_lnjt.setText("ok");

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        }else {
            finishQuiz();
        }
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                UpdateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                UpdateCountDownText();
                checkJawaban();
            }
        }.start();
    }

    private void UpdateCountDownText(){
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

        text_waktu.setText(timeFormatted);

        if(timeLeftInMillis < 10000){
            text_waktu.setTextColor(Color.RED);
        }else {
            text_waktu.setTextColor(textColorDefaultcd);
        }
    }

    private void checkJawaban(){
        jawabann = true;

        countDownTimer.cancel();

        RadioButton rbsSelected = findViewById(radiog.getCheckedRadioButtonId());

        int jawabann = radiog.indexOfChild(rbsSelected);

        if(jawabann == currentSoal.getJawaban()){
            score++;
            text_score.setText("score : " + score);
        }
        
        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch (currentSoal.getJawaban()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                text_soal.setText("Jawaban yang benar A");
            break;
            case 2:
                rb1.setTextColor(Color.GREEN);
                text_soal.setText("Jawaban yang benar B");
            break;
            case 3:
                rb1.setTextColor(Color.GREEN);
                text_soal.setText("Jawaban yang benar C");
            break;
            case 4:
                rb1.setTextColor(Color.GREEN);
                text_soal.setText("Jawaban yang benar D");
            break;
        }

        if(soalCounter < soalCountTotal){
            btn_lnjt.setText("Lanjut");
        }else {
            btn_lnjt.setText("Selesai");
        }

    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            finishQuiz();
        }else{
            Toast.makeText(this,"tekan kembali untuk selesai",Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }
}
