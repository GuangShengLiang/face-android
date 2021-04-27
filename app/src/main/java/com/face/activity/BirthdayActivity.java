package com.face.activity;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import face.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BirthdayActivity extends AppCompatActivity {

    @BindView(R.id.ev_nick_name)
    EditText nickName;
//    @BindView(R.id.titlebar)
//    CustomTitleBar titleBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nick_name);
        ButterKnife.bind(this);

        nickName.setText(getIntent().getStringExtra("nickName"));
//        titleBar.setLeftIconOnClickListener(v -> finish());
//        titleBar.setRightTextOnClickListener(v -> {
//                    finish();
//                }
//        );
    }
}
