package com.face.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import face.R;
import com.face.MainActivity;
import com.face.util.PreferencesUtil;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

public class SettingActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.btn_logout)
    Button mLogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
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
                Log.d("tt", "right");
            }
        });
    }

    @OnClick(R.id.btn_logout)
    public void onClick(View view) {
        // 清除sharedpreferences中存储信息
        PreferencesUtil.getInstance().setLogin(false);
        // 清除通讯录
        // 清除朋友圈
        // 跳转至登录页
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
