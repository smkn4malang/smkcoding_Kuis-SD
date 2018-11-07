package com.tiara.app.kuissd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class ScoreActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView hasil = (TextView) findViewById(R.id.hasil);
        TextView nilai = (TextView) findViewById(R.id.nilai);

        hasil.setText("Jawaban Benar : " + GameActivity3.benar + "\nJawaban Salah : " + GameActivity3.salah);
        nilai.setText("" + GameActivity3.hasil);
    }

    public void ulangi(View view) {
        finish();
        Intent intent = new Intent(getApplicationContext(), GameActivity3.class);
        startActivity(intent);
    }

    public void lanjut(View view) {
        Intent m = new Intent(this, ClassActivity.class);
        startActivity(m);
    }
}
