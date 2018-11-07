package com.tiara.app.kuissd;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class GameActivity3 extends AppCompatActivity {

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
            "Bilangan A berada di antara 355 dan 357. Bilangan A jika ditambah 100 maka hasilnya adalah ....",
            "5 – 17 – 29 – 41 – 53 – 65\n" +
                    "Barisan bilangan di atas adalah barisan bilangan loncat ....\n",
            "Barisan bilangan meloncat 6 dimulai dari 6 adalah ....",
            "Hasil dari 3.147 + 1.897 adalah ....",
            "Bilangan tujuh ribu delapan ratus enam puluh lima ditulis ....",
            "Pak Dadang telah memanen semangka sebanyak 1.325 buah. Sebanyak 720 semangka dijual ke pasar. Semangka yang masih terjual adalah sebanyak ....",
            "Selisih antara nilai angka 6 dan 4 pada bilangan 6.245 adalah ....",
            "25 x 12 = ....",
            "59 x 31 = ....",
            "Pak Jaya membeli 9 kardus mie instan. Setiap kardus berisi 24 bungkus mie. Berapa bungkus jumlah seluruh mie yang dibeli Pak jaya?"
    };

    String[] pilihan_kuis = new String[]{
            "455","457","456","458",
            "17","12","5","15",
            "6 – 12 – 18 – 21 – 27","6 – 12 – 18 – 24 – 30","6 – 12 – 15 – 22 – 30","6 – 12 – 18 – 22 - 30",
            "5.134","5.174","5.244","5.044",
            "7.865","7.568","70.568","78.605",
            "705","605","695","755",
            "5.900","2.000","2","5.960",
            "200","300","250","350",
            "1829","1.209","1.329","1.928",
            "226 bungkus","206 bungkus","196 bungkus","216 bungkus",
    };

    String[] jawaban = new String[]{
            "456",
            "12",
            "6 – 12 – 18 – 24 – 30",
            "5.044",
            "7.865",
            "605",
            "2",
            "300",
            "1829",
            "216 bungkus",

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

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
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "tekan kembali untuk selesai", Toast.LENGTH_SHORT).show();
        }
        UpdateCountDownText();

        backPressedTime = System.currentTimeMillis();
    }

    private void UpdateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        text_waktu.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            text_waktu.setTextColor(Color.RED);
        } else {
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
                Intent selesai = new Intent(getApplicationContext(), ScoreActivity2.class);
                startActivity(selesai);
                finishQuiz();
            }
        } else {
            Toast.makeText(this, "Pilih Jawaban dulu", Toast.LENGTH_SHORT).show();
        }
    }
}
