package com.example.face.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.face.R;
import com.example.face.util.PreferencesUtil;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private Button mLogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        PreferencesUtil.getInstance().init(this);
        initView();
    }

    private void initView() {
        mLogoutBtn = findViewById(R.id.btn_logout);

        mLogoutBtn.setOnClickListener(this);
    }

    public void back(View view) {
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_logout:
                // 清除sharedpreferences中存储信息
                PreferencesUtil.getInstance().setLogin(false);
                PreferencesUtil.getInstance().setUser(null);

                // 清除通讯录
//                User.deleteAll(User.class);
                // 清除朋友圈
//                FriendsCircle.deleteAll(FriendsCircle.class);

                // 跳转至登录页
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();

                break;
        }
    }
}
