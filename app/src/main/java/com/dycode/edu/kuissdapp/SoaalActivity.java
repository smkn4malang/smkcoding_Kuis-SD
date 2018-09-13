package com.dycode.edu.kuissdapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SoaalActivity extends AppCompatActivity {

    TextView text_no;
    TextView text_soal;
    RadioGroup radiog;
    RadioButton rb1, rb2, rb3, rb4;
    int nomor = 0;
    public static int hasil, benar, salah;

    String[] no = new String[] {
            "1/10",
            "2/10",
            "3/10",
            "4/10",
            "5/10",
            "6/10",
            "7/10",
            "8/10",
            "9/10",
            "10/10"
    };

    String[] soal = new String[] {
            "5+5=....",
            "10-3=...",
            "7+10=...",
            "9-8=....",
            "20-7=...",
            "10+20=...",
            "Pak Amir mempunyai apel ssebanyak 10 dan diminta adi 3 berapa sisa apel yang dimiliki Pak Amir...",
            "Ara membeli labu 3 dan membeli lagi 8 berapa jumblah labu Ara sekarang...",
            "Intan mempunyai baju baru sebanyak 10 buah lalu ibu memberi baju baru kepada intan sebanyak 2 buah berapa jumblah baju Intan sekarang...",
            "Nita mempunyai sepatu 6 pasang lalu dia menjualnya sebanyak 4 pasang berapa jumbah sepatu Nita sekarang..."
    };

    String[] jawaban = new String[] {
            "6","8","10","7",
            "8","7","5","10",
            "17","20","5","9",
            "3","7","6","1",
            "14","13","8","11",
            "21","27","30","15",
            "6 apel","5 apel","7 apel","3 apel",
            "11 labu","13 labu","10 labu","14 labu",
            "10 baju","13 baju","15 baju","12 baju",
            "1 pasang","5 pasang","3 pasang","2 pasang",
    };

    String[] jawaban_benar = new String[] {
            "10",
            "7",
            "17",
            "1",
            "13",
            "30",
            "7 apel",
            "11 labu",
            "12 baju",
            "2 pasang",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soaal);

        text_no = (TextView)findViewById(R.id.text_no);
        text_soal = (TextView)findViewById(R.id.text_soal);
        radiog = (RadioGroup)findViewById(R.id.radiog);
        rb1 = (RadioButton)findViewById(R.id.rb1);
        rb2 = (RadioButton)findViewById(R.id.rb2);
        rb3 = (RadioButton)findViewById(R.id.rb3);
        rb4 = (RadioButton)findViewById(R.id.rb4);

        text_soal.setText(soal[nomor]);
        rb1.setText(jawaban[0]);
        rb2.setText(jawaban[1]);
        rb3.setText(jawaban[2]);
        rb4.setText(jawaban[3]);

        radiog.check(0);
        benar = 0;
        salah = 0;


    }

    public void next(View view) {
        if(rb1.isChecked()||rb2.isChecked()||rb3.isChecked()||rb4.isChecked()) {
            RadioButton jawaban_user = (RadioButton) findViewById(radiog.getCheckedRadioButtonId());
            String ambil_jawaban_user = jawaban_user.getText().toString();
            radiog.check(0);
            if (ambil_jawaban_user.equalsIgnoreCase(jawaban_benar[nomor])) benar++;
            else salah++;
            nomor++;
            if (nomor < soal.length) {
                text_soal.setText(soal[nomor]);
                rb1.setText(jawaban[(nomor * 4) + 0]);
                rb2.setText(jawaban[(nomor * 4) + 1]);
                rb3.setText(jawaban[(nomor * 4) + 2]);
                rb4.setText(jawaban[(nomor * 4) + 3]);

            } else {
                hasil = benar * 10;
                Intent selesai = new Intent(getApplicationContext(), SkorrActivity.class);
                startActivity(selesai);
            }
        }
        else {
            Toast.makeText(this,"pilih jawaban dulu",Toast.LENGTH_SHORT).show();
        }
    }
}
