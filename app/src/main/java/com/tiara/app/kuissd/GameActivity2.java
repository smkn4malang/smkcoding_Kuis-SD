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

public class GameActivity2 extends AppCompatActivity {

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
            "nama bilangan 437 adalah ….",
            "Bilangan genap antara 10 sampai 20 adalah ….",
            "Pada bilangan 429 angka 4 menempati tempat ….",
            "adik mempunyai kelereng 178 butir, kemudian membeli lagi 69 butir. Maka\n" +
                    "kelereng adik sekarang ….\n",
            "pukul setengah empat ditulis ….",
            "Kedua jarum jam akan menujuk angka yang sama pada pukul...",
            "Jarum pendek dan jarum panjang pada jam analog akan membentuk garis lurus pada pukul...",
            "Mobil jemputan “PIPIT” berangkat dari rumah pukul 06.00 dan sampai di sekolah pukul 08.00. Lama perjalanan mobil itu adalah...",
            "Ayah bekerja di kebun dari pulul 10.00 pagi sampai pukul 04.00. Lama ayah bekerja adalah...",
            "Adik Via tidur dari pukul 20.00 dan terbangun pada pukul 04.00. Adik Via tidur selama :"
    };

    String[] pilihan_kuis = new String[]{
            "empat ratus tiga puluh tujuh", "empat ratus tiga tujuh", "empat tiga puluh tujuh", "empat tiga tujuh",
            "12, 14, 16, dan 18", "12, 13, 16, dan 18", "12, 14, 16, dan 20", "10, 14, 16, dan 18",
            "Puluhan", "Ratusan", "Satuan", "Ribuan",
            "246", "147", "247", "249",
            "05.30", "04.30", "03.30", "06.30",
            "05.00", "08.00", "12.00", "06.00",
            "05.00", "08.00", "07.00", "06.00",
            "10 jam", "5 jam", "2 jam", "8 jam",
            "6 jam", "11 jam", "12 jam", "7 jam",
            "7 jam", "8 jam", "5 jam", "4 jam",
    };

    String[] jawaban = new String[]{
            "empat ratus tiga puluh tujuh",
            "12, 14, 16, dan 18",
            "Ratusan",
            "247",
            "03.30",
            "12.00",
            "06.00",
            "2 jam",
            "6 jam",
            "8 jam",

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
                Intent selesai = new Intent(getApplicationContext(), ScoreActivity2.class);
                startActivity(selesai);
                finishQuiz();
            }
        } else {
            Toast.makeText(this, "Pilih Jawaban dulu", Toast.LENGTH_SHORT).show();
        }
    }

}
