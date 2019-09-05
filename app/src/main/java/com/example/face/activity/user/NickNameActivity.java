package com.example.face.activity.user;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.face.R;
import com.example.face.http.AccountHTTP;
import com.example.face.http.HTTPFactory;
import com.example.face.model.Account;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NickNameActivity extends AppCompatActivity {

    @BindView(R.id.ev_nick_name)
    EditText nickName;
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    AccountHTTP ah = HTTPFactory.getAccountHTTP();
    @Nullable
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nick_name);
        ButterKnife.bind(this);
        Account a = ah.myInfo();
        nickName.setText(getIntent().getStringExtra("nickName"));
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {
Log.d("tt","right");
            }
        });
    }
}
