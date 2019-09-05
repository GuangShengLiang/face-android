package com.example.face.activity.user;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import java.io.IOException;

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
        Runnable runnable =new Runnable(){
            @Override
            public void run(){
                //进行访问网络操作
//                Message msg = Message.obtain();
//                Bundle data = new Bundle();
                Account acc = new Account();
                try {
                    acc = ah.myInfo().execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                nickName.setText(acc.getNickName());
//                data.putString("value", acc.getNickName());
//                msg.setData(data);
//                handler.sendMessage(msg);
            }
        };
        new Thread(runnable).start();
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
