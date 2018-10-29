package com.tiarar.app.kuis_sd;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mulai_btn)
    Button btnMulai;
    @BindView(R.id.skor_btn)
    Button btnSkor;

    private Button btnNama, btnOk, btnCancel;
    private EditText nama;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnNama = findViewById(R.id.nama_btn);
        btnNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_name);
                btnOk = dialog.findViewById(R.id.btn_ok);
                btnCancel = dialog.findViewById(R.id.btn_cancel);
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nama = dialog.findViewById(R.id.nama);
                        final String name = nama.getText().toString().trim();
                        dialog.dismiss();
                        btnNama.setText(name);
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @OnClick({R.id.mulai_btn, R.id.skor_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mulai_btn:
                intent = new Intent(MainActivity.this, SubjectActivity.class);
                startActivity(intent);
                break;
            case R.id.skor_btn:
                intent = new Intent(MainActivity.this, LeaderboardActivity.class);
                startActivity(intent);
                break;
        }
    }
}
