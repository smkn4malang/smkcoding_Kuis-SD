package com.dycode.edu.kuissdapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Edit extends AppCompatActivity {

    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.btnO)
    Button btnO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    @OnClick(R.id.btnO)
    public void onViewCLicked(){
        String nama = et.getText().toString();
        Intent m = new Intent();
        m.putExtra(EXTRA_NAMA,nama);
        setResult(RESULT_OK, m);
        finish();
    }
}
