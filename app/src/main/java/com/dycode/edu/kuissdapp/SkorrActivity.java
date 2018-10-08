package com.dycode.edu.kuissdapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SkorrActivity extends AppCompatActivity {

   /* Button btnlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skorr);

        TextView hasil = (TextView)findViewById(R.id.hasil);
        TextView nilai = (TextView)findViewById(R.id.nilai);
        hasil.setText("jawban benar : "+SoaalActivity.benar+"\njawaban salah : "+SoaalActivity.salah);
        nilai.setText(""+SoaalActivity.hasil);

        btnlan=(Button)findViewById(R.id.btnlan);

        btnlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SkorrActivity.this,KelasActivity.class);
                startActivity(i);
            }
        });
    }

    public void next(View view) {
        finish();
        Intent i = new Intent(getApplicationContext(),SoaalActivity.class);
        startActivity(i);
    }*/
}
