package com.tiara.app.kuissd;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class GameActivity extends AppCompatActivity {


    TextView text_soal;
    RadioGroup radiog;
    RadioButton rb1, rb2, rb3, rb4;
    Button btn_lnjt;
    int nomor = 0;
    public static int hasil, benar, salah;
    private long backPressedTime;
    TextView text_waktu;
    private ColorStateList textColorDefaultcd;
    private long timeLeftInMillis;
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    String[] soal_kuis = new String[]{
            "5+1",
            "6-5",
            "7+6",
            "5+6",
            "6+6",
            "20-10",
            "10-5",
            "4+6",
            "7+5",
            "6+1"
    };

    String[] pilihan_kuis = new String[]{
            "5", "6", "4", "3",
            "1", "4", "8", "9",
            "7", "8", "13", "6",
            "11", "5", "10", "9",
            "13", "12", "8", "7",
            "8", "15", "6", "10",
            "5", "8", "7", "9",
            "10", "5", "7", "8",
            "10", "11", "12", "7",
            "7", "8", "5", "4",
    };

    String[] jawaban = new String[]{
            "6",
            "1",
            "13",
            "11",
            "12",
            "10",
            "5",
            "10",
            "12",
            "7",
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        text_soal = (TextView) findViewById(R.id.text_soal);
        radiog = (RadioGroup) findViewById(R.id.radiog);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        rb4 = (RadioButton) findViewById(R.id.rb4);
        text_waktu = findViewById(R.id.waktu);

        textColorDefaultcd = text_waktu.getTextColors();
        timeLeftInMillis = COUNTDOWN_IN_MILLIS;

        text_soal.setText(soal_kuis[nomor]);
        rb1.setText(pilihan_kuis[0]);
        rb2.setText(pilihan_kuis[1]);
        rb3.setText(pilihan_kuis[2]);
        rb4.setText(pilihan_kuis[3]);
        radiog.check(0);
        benar = 0;
        salah = 0;


    }



    private void finishQuiz() {
        Intent resultIntent = new Intent();
       // resultIntent.putExtra(EXTRA_SCORE, score);
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
        UpdateCountDownText();

        backPressedTime = System.currentTimeMillis();
    }

    private void UpdateCountDownText() {
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

    public void next(View view) {
        if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
            RadioButton jawabanUser = (RadioButton) findViewById(radiog.getCheckedRadioButtonId());
            String ambilJawabanUser = jawabanUser.getText().toString();
            radiog.check(0);
            if (ambilJawabanUser.equalsIgnoreCase(jawaban[nomor])) benar++;
            else salah++;
            nomor++;
            if (nomor < soal_kuis.length) {
                text_soal.setText(soal_kuis[nomor]);
                rb1.setText(pilihan_kuis[(nomor * 4) + 0]);
                rb2.setText(pilihan_kuis[(nomor * 4) + 1]);
                rb3.setText(pilihan_kuis[(nomor * 4) + 2]);
                rb4.setText(pilihan_kuis[(nomor * 4) + 3]);
            } else {
                hasil = benar * 10;
                Intent selesai = new Intent(getApplicationContext(), ScoreActivity.class);
                startActivity(selesai);
                finishQuiz();
            }
        } else {
            Toast.makeText(this, "Pilih Jawaban dulu", Toast.LENGTH_SHORT).show();
        }
    }

}
