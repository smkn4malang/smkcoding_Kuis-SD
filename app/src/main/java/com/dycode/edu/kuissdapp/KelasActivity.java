package com.dycode.edu.kuissdapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class KelasActivity extends AppCompatActivity {

    private Button btnk1;
    private Button btnk2;
    private Button btnk3;
    private Button btnk4;
    private Button btnk5;
    private Button btnk6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelas);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnk1 = (Button)findViewById(R.id.btnk1);
        btnk2 = (Button)findViewById(R.id.btnk2);
        btnk3 = (Button)findViewById(R.id.btnk3);
        btnk4 = (Button)findViewById(R.id.btnk4);
        btnk5 = (Button)findViewById(R.id.btnk5);
        btnk6 = (Button)findViewById(R.id.btnk6);

        btnk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m = new Intent(KelasActivity.this,SoaalActivity.class);
                startActivity(m);
            }
        });

        btnk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m = new Intent(KelasActivity.this,SoaalActivity.class);
                startActivity(m);
            }
        });

        btnk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m = new Intent(KelasActivity.this,SoaalActivity.class);
                startActivity(m);
            }
        });

        btnk4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m = new Intent(KelasActivity.this,SoaalActivity.class);
                startActivity(m);
            }
        });

        btnk5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m = new Intent(KelasActivity.this,SoaalActivity.class);
                startActivity(m);
            }
        });

        btnk6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m = new Intent(KelasActivity.this,SoaalActivity.class);
                startActivity(m);
            }
        });
    }
}
