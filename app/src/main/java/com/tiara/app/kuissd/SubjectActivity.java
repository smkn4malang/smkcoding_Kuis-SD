package com.tiara.app.kuissd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubjectActivity extends AppCompatActivity {

    @BindView(R.id.matematika_btn)
    Button btnMatematika;
    @BindView(R.id.ipa_btn)
    Button btnIpa;
    @BindView(R.id.bi_btn)
    Button btnBi;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        ButterKnife.bind(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    @OnClick({R.id.matematika_btn, R.id.ipa_btn, R.id.bi_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.matematika_btn:
                intent = new Intent(this, VideoActivity.class);
                startActivity(intent);
                break;
            case R.id.ipa_btn:
                intent = new Intent(this, VideoActivity.class);
                startActivity(intent);
                break;
            case R.id.bi_btn:
                intent = new Intent(this, VideoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
