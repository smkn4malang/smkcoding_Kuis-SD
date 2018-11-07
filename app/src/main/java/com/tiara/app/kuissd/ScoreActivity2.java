package com.tiara.app.kuissd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class ScoreActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView hasil = (TextView) findViewById(R.id.hasil);
        TextView nilai = (TextView) findViewById(R.id.nilai);

        hasil.setText("Jawaban Benar : "+GameActivity2.benar+"\nJawaban Salah : "+GameActivity2.salah);
        nilai.setText(""+GameActivity2.hasil);
    }

    public void ulangi(View view){
        finish();
        Intent intent = new Intent(getApplicationContext(), GameActivity2.class);
        startActivity(intent);
    }

    public void lanjut (View view){
        Intent m = new Intent(this, ClassActivity.class);
        startActivity(m);
    }
}
