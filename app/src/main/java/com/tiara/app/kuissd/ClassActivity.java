package com.tiara.app.kuissd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClassActivity extends AppCompatActivity {

    @BindView(R.id.btnk1)
    Button btnk1;
    @BindView(R.id.btnk2)
    Button btnk2;
    @BindView(R.id.btnk3)
    Button btnk3;
    @BindView(R.id.btnk4)
    Button btnk4;
    @BindView(R.id.btnk5)
    Button btnk5;
    @BindView(R.id.btnk6)
    Button btnk6;
    private long backPressedTime;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        ButterKnife.bind(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    @OnClick({R.id.btnk1, R.id.btnk2, R.id.btnk3, R.id.btnk4, R.id.btnk5, R.id.btnk6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnk1:
                intent = new Intent(this, GameActivity.class);
                startActivity(intent);
                break;
            case R.id.btnk2:
                intent = new Intent(this, GameActivity2.class);
                startActivity(intent);
                break;
            case R.id.btnk3:
                intent = new Intent(this, GameActivity3.class);
                startActivity(intent);
                break;
            case R.id.btnk4:
                intent = new Intent(this, GameActivity.class);
                startActivity(intent);
                break;
            case R.id.btnk5:
                intent = new Intent(this, GameActivity.class);
                startActivity(intent);
                break;
            case R.id.btnk6:
                intent = new Intent(this, GameActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            Intent n = new Intent(this, MainActivity.class);
            startActivity(n);
        } else {
            Toast.makeText(this, "tekan kembali", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}
