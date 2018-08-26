package com.dycode.edu.kuissdapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogActivity extends AppCompatActivity {

    private static  final int RC_EDIT_NAMA=117;
    @BindView(R.id.tn)
    TextView tn;
    @BindView(R.id.btnG)
    Button btnG;
    @BindView(R.id.btno)
    Button btno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_name);
        ButterKnife.bind(this);

        btno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(DialogActivity.this, MainActivity.class);
                startActivity(n);
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    @OnClick(R.id.btnG)
    public void onViewClicked(){
        Intent m = new Intent(this,Edit.class);
        startActivityForResult(m, RC_EDIT_NAMA);

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==RC_EDIT_NAMA && resultCode==RESULT_OK){
            String nama = data.getExtras().getString(Edit.EXTRA_NAMA);
            tn.setText(nama);
        }
    }
}
