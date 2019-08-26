package com.example.face.activity.user;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.face.R;
import com.example.face.view.CustomTitleBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NickNameActivity extends AppCompatActivity {

    @BindView(R.id.ev_nick_name)
    EditText nickName;
    @BindView(R.id.titlebar)
    CustomTitleBar titleBar;
    @Nullable
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nick_name);
        ButterKnife.bind(this);

        nickName.setText(getIntent().getStringExtra("nickName"));
        titleBar.setLeftIconOnClickListener(v -> finish());
        titleBar.setRightTextOnClickListener(v -> {
                    finish();
                }
        );
    }
}
